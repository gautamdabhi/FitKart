package mrkinfotech.fitkart.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import mrkinfotech.fitkart.databinding.ItemCommonBinding

class OrderAdapter(private val orderList: List<Map<String, Any>>) :
    RecyclerView.Adapter<OrderAdapter.OrderViewHolder>() {

    inner class OrderViewHolder(val binding: ItemCommonBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrderViewHolder {
        val binding = ItemCommonBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return OrderViewHolder(binding)
    }

    override fun onBindViewHolder(holder: OrderViewHolder, position: Int) {
        val order = orderList[position]
        holder.binding.apply {
            textviewItemName.text = "Order #${order["orderId"]}"

            // Fallback logic for keys: checks totalAmount then total
            val amount = order["totalAmount"] ?: order["total"] ?: "0.00"
            val status = order["status"] ?: "Confirmed"

            textviewItemPrice.text = "Amount: $amount | Status: $status"
            imageviewitem.visibility = View.GONE
        }
    }

    override fun getItemCount() = orderList.size
}