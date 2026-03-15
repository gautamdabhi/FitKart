package mrkinfotech.fitkart.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import mrkinfotech.fitkart.R
import mrkinfotech.fitkart.databinding.ItemCommonBinding
import mrkinfotech.fitkart.models.CommonDataClass

class CartAdapter(
    private val context: Context,
    private var cartList: ArrayList<CommonDataClass>,
    private val onDeleteClick: (CommonDataClass) -> Unit
) : RecyclerView.Adapter<CartAdapter.CartViewHolder>() {

    inner class CartViewHolder(val binding: ItemCommonBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartViewHolder {
        val binding = ItemCommonBinding.inflate(LayoutInflater.from(context), parent, false)
        return CartViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CartViewHolder, position: Int) {
        val item = cartList[position]
        holder.binding.apply {
            textviewItemName.text = item.itemName
            textviewItemPrice.text = "₹${item.itemPrice}"

            Glide.with(context)
                .load(item.image)
                .placeholder(R.drawable.image_warning)
                .centerCrop()
                .into(imageviewitem)

            root.setOnLongClickListener {
                onDeleteClick(item)
                true
            }
        }
    }

    override fun getItemCount(): Int = cartList.size

    fun updateData(newList: ArrayList<CommonDataClass>) {
        cartList = newList
        notifyDataSetChanged()
    }
}