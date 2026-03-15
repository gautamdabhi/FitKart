package mrkinfotech.fitkart.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import mrkinfotech.fitkart.R

class ImageSliderAdapter(
    private val context: Context,
    private val imageList: ArrayList<String>
) : RecyclerView.Adapter<ImageSliderAdapter.ViewHolder>() {

    // This matches the ID "imageView" in your item_image_slider.xml
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val imageView: ImageView = view.findViewById(R.id.imageView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_image_slider, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        // Glide loads the verified URLs from MasterDataUtils
        Glide.with(context)
            .load(imageList[position])
            .placeholder(R.drawable.image_warning) // Placeholders prevent blank white screens
            .error(R.drawable.image_warning)
            .into(holder.imageView)
    }

    override fun getItemCount(): Int = imageList.size
}