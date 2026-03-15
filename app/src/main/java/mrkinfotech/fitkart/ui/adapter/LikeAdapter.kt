package mrkinfotech.fitkart.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import mrkinfotech.fitkart.R
import mrkinfotech.fitkart.databinding.ItemCommonBinding
import mrkinfotech.fitkart.models.CommonDataClass

class LikeAdapter(
    private val context: Context,
    private var likeList: ArrayList<CommonDataClass>,
    private val onItemClick: (CommonDataClass) -> Unit
) : RecyclerView.Adapter<LikeAdapter.LikeViewHolder>() {

    inner class LikeViewHolder(val binding: ItemCommonBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LikeViewHolder {
        val binding = ItemCommonBinding.inflate(LayoutInflater.from(context), parent, false)
        return LikeViewHolder(binding)
    }

    override fun onBindViewHolder(holder: LikeViewHolder, position: Int) {
        val product = likeList[position]
        holder.binding.apply {
            textviewItemName.text = product.itemName
            textviewItemPrice.text = "₹${product.itemPrice}"

            Glide.with(context)
                .load(product.image)
                .placeholder(R.drawable.image_warning)
                .centerCrop()
                .into(imageviewitem)

            root.setOnClickListener { onItemClick(product) }
        }
    }

    override fun getItemCount(): Int = likeList.size

    fun updateData(newList: ArrayList<CommonDataClass>) {
        this.likeList = newList
        notifyDataSetChanged()
    }
}