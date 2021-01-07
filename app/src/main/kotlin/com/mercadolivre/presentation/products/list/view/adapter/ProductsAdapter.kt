package com.mercadolivre.presentation.products.list.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.mercadolivre.R
import com.mercadolivre.core.extensions.toCurrency
import com.mercadolivre.domain.entities.ProductsContent

class ProductsAdapter(
    private val onItemClick: (product: ProductsContent.Product) -> Unit
) : RecyclerView.Adapter<ProductsAdapter.ProductViewHolder>() {

    private var products: List<ProductsContent.Product> = arrayListOf()

    fun addProducts(products: List<ProductsContent.Product>) {
        this.products = products
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = products.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_view_holder_product, parent, false)
        return ProductViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        holder.bind(products[position])
    }

    inner class ProductViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val card: CardView = itemView.findViewById(R.id.cardItemProduct)
        val image: ImageView = itemView.findViewById(R.id.imageItemProduct)
        val title: TextView = itemView.findViewById(R.id.textItemProductTitle)
        val description: TextView = itemView.findViewById(R.id.textItemProductDescription)
        val price: TextView = itemView.findViewById(R.id.textItemProductPrice)

        fun bind(product: ProductsContent.Product) {
            Glide.with(image.context)
                .load(product.thumbnail)
                .centerCrop()
                .placeholder(R.drawable.ic_image_detail_placeholder)
                .error(R.drawable.ic_image_detail_placeholder_error)
                .into(image)

            title.text = product.title
            price.text = product.price.toCurrency()

            card.setOnClickListener { onItemClick(product) }
        }
    }

}