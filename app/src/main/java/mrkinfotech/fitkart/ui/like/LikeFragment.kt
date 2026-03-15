package mrkinfotech.fitkart.ui.like

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import mrkinfotech.fitkart.databinding.FragmentLikeBinding
import mrkinfotech.fitkart.models.CommonDataClass
import mrkinfotech.fitkart.ui.adapter.LikeAdapter
import mrkinfotech.fitkart.ui.details.ProductDetailsActivity

class LikeFragment : Fragment() {
    private var _binding: FragmentLikeBinding? = null
    private val binding get() = _binding!!
    private val db = FirebaseFirestore.getInstance()
    private val userId = FirebaseAuth.getInstance().currentUser?.uid ?: ""

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, s: Bundle?): View {
        _binding = FragmentLikeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loadWishlistFromFirebase()
    }

    private fun loadWishlistFromFirebase() {
        if (userId.isEmpty()) return

        db.collection("wishlist").document(userId).collection("items").get()
            .addOnSuccessListener { docs ->
                val list = ArrayList<CommonDataClass>()
                for (doc in docs) {
                    val item = doc.toObject(CommonDataClass::class.java)
                    list.add(item)
                }

                if (_binding == null) return@addOnSuccessListener

                if (list.isEmpty()) {
                    binding.rvLikedItems.visibility = View.GONE
                    binding.tvEmptyWishlist.visibility = View.VISIBLE
                } else {
                    binding.rvLikedItems.visibility = View.VISIBLE
                    binding.tvEmptyWishlist.visibility = View.GONE
                    binding.rvLikedItems.layoutManager = GridLayoutManager(requireContext(), 2)
                    binding.rvLikedItems.adapter = LikeAdapter(requireContext(), list) { product ->
                        val intent = Intent(requireContext(), ProductDetailsActivity::class.java)
                        intent.putExtra("product_data", product)
                        startActivity(intent)
                    }
                }
            }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}