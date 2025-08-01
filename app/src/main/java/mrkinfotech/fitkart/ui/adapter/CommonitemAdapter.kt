package mrkinfotech.fitkart.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import mrkinfotech.fitkart.R
import mrkinfotech.fitkart.databinding.ItemCommonBinding
import mrkinfotech.fitkart.ui.data.CommonDataClass


class ItemAdapter(
    val context: Context,
    private var itemList: ArrayList<CommonDataClass>,
    private val onClickListener: OnClickListener
) : RecyclerView.Adapter<ItemAdapter.ItemViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val binding = ItemCommonBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.bind(itemList[position], onClickListener)
    }

    override fun getItemCount(): Int = itemList.size

    class OnClickListener(val clickListener: (itemData: CommonDataClass, clickType: Int) -> Unit) {
        fun onClick(itemData: CommonDataClass, clickType: Int) = clickListener(itemData, clickType)
    }

    inner class ItemViewHolder(private val binding: ItemCommonBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(dataModal: CommonDataClass, onClickListener: OnClickListener) {
            Glide.with(context)
                .load(dataModal.image)
                .centerCrop()
                .placeholder(R.drawable.ic_app_logo)
                .into(binding.imageviewitem)
            binding.textviewItemName.text = dataModal.itemName
            binding.textviewitemDescription.text=dataModal.itemDescription
            binding.textviewItemPrice.text=dataModal.itemPrice


        }
    }
}
