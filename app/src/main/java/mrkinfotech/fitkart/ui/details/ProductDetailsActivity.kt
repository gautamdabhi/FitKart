package mrkinfotech.fitkart.ui.details

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import mrkinfotech.fitkart.R
import mrkinfotech.fitkart.databinding.ActivityProductDetailsBinding
import mrkinfotech.fitkart.models.CommonDataClass
import mrkinfotech.fitkart.ui.home.HomeMainActivity

class ProductDetailsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityProductDetailsBinding
    private val db = FirebaseFirestore.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProductDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val product = intent.getSerializableExtra("product_data") as? CommonDataClass

        product?.let { item ->
            // All these IDs now exist in the XML above
            binding.tvDetailName.text = item.itemName
            binding.tvDetailPrice.text = "₹${item.itemPrice}"
            binding.tvDetailDesc.text = item.itemDescription
            binding.tvDetailCategory.text = item.category

            Glide.with(this)
                .load(item.image)
                .placeholder(R.drawable.image_warning)
                .into(binding.ivDetailImage)

            binding.btnAddToCart.setOnClickListener {
                val user = FirebaseAuth.getInstance().currentUser
                if (user == null) {
                    Toast.makeText(this, "Error: User not logged in", Toast.LENGTH_SHORT).show()
                    return@setOnClickListener
                }

                val data = hashMapOf(
                    "id" to item.id,
                    "itemName" to item.itemName,
                    "itemPrice" to item.itemPrice,
                    "image" to item.image,
                    "itemDescription" to item.itemDescription,
                    "category" to item.category
                )

                db.collection("cart").document(user.uid).collection("items").document(item.id)
                    .set(data)
                    .addOnSuccessListener {
                        Toast.makeText(this, "Item added to Cart!", Toast.LENGTH_SHORT).show()
                        val intent = Intent(this, HomeMainActivity::class.java)
                        intent.putExtra("SHOW_CART", true)
                        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP
                        startActivity(intent)
                        finish()
                    }
                    .addOnFailureListener { Toast.makeText(this, "Firebase Error", Toast.LENGTH_SHORT).show() }
            }

            binding.btnLike.setOnClickListener {
                val user = FirebaseAuth.getInstance().currentUser ?: return@setOnClickListener
                db.collection("wishlist").document(user.uid).collection("items").document(item.id).set(item)
                    .addOnSuccessListener { Toast.makeText(this, "Saved to Wishlist", Toast.LENGTH_SHORT).show() }
            }

            binding.btnCartShortcut.setOnClickListener {
                val intent = Intent(this, HomeMainActivity::class.java)
                intent.putExtra("SHOW_CART", true)
                startActivity(intent)
            }
        }
    }
}