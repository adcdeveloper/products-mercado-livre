package com.mercadolivre.presentation.products.detail.view.fragment.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.mercadolivre.R

class ImagesAdapter(
    private val images: List<String>,
    private val onItemClick: (String) -> Unit
) : RecyclerView.Adapter<ImagesAdapter.ImageViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_view_holder_product_detail_image, parent, false)
        return ImageViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        holder.bind(images[position])
    }

    override fun getItemCount(): Int = images.size

    inner class ImageViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val cardView: CardView =
            itemView.findViewById(R.id.cardItemProductDetailContent)
        private val image: ImageView = itemView.findViewById(R.id.imageItemProductDetailImage)

        fun bind(thumbnail: String) {
            loadImage(thumbnail)
            cardView.setOnClickListener { onItemClick(thumbnail) }
        }

        private fun loadImage(thumbnail: String) {
            Glide.with(image.context)
                .load(thumbnail)
                .centerCrop()
                .placeholder(R.drawable.ic_image_detail_placeholder)
                .error(R.drawable.ic_image_detail_placeholder_error)
                .into(image)
        }
    }
}