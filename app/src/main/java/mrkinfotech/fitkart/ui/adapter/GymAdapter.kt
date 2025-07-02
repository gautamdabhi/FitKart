package mrkinfotech.fitkart.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import mrkinfotech.fitkart.R
import mrkinfotech.fitkart.databinding.ItemlayoutBinding
import mrkinfotech.fitkart.ui.data.Gym


class ItemAdapter(
    val context: Context,
    private var itemList: ArrayList<Gym>,
    private val onClickListener: OnClickListener
) : RecyclerView.Adapter<ItemAdapter.ItemViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val binding = ItemlayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.bind(itemList[position], onClickListener)
    }

    override fun getItemCount(): Int = itemList.size

    class OnClickListener(val clickListener: (itemData: Gym, clickType: Int) -> Unit) {
        fun onClick(itemData: Gym, clickType: Int) = clickListener(itemData, clickType)
    }

    inner class ItemViewHolder(private val binding: ItemlayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(dataModal: Gym, onClickListener: OnClickListener) {
            binding.itemName1.text = dataModal.dataItemName
            Glide.with(context)
                .load(dataModal.titleImage)
                .placeholder(R.drawable.ic_app_logo)
                .into(binding.card1)

        }
    }
}
