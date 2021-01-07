package com.mercadolivre.presentation.products.detail.view.fragment

import android.content.res.Resources
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.mercadolivre.R
import com.mercadolivre.domain.entities.ProductDetail
import com.mercadolivre.presentation.products.detail.state.ProductDetailState
import com.mercadolivre.presentation.products.detail.view.fragment.adapter.ImagesAdapter
import com.mercadolivre.presentation.products.detail.viewmodel.ProductDetailViewModel
import kotlinx.android.synthetic.main.fragment_product_detail.*
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class ProductDetailFragment : Fragment() {

    private val viewModel: ProductDetailViewModel by sharedViewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setupViewModel()
        return inflater.inflate(R.layout.fragment_product_detail, container, false)
    }

    private fun setupViewModel() {
        viewModel.productDetailState.observe(viewLifecycleOwner, { state ->
            when (state) {
                is ProductDetailState.ShowDetail -> {
                    updateStateDetail(state.thumbnail, state.detail)
                }
                is ProductDetailState.ShowError -> {
                    updateStateError()
                }
            }
        })
    }

    private fun updateStateDetail(thumbnail: String, detail: ProductDetail) {
        makeHeightProportionalToWidthOfScreen()
        hideLoading()

        loadImageHighLight(thumbnail)

        val adapter = ImagesAdapter(detail.pictures) { loadImageHighLight(it) }
        recyclerProductDetailImages.adapter = adapter

        textProductDetailTitle.text = detail.title
        textProductDetailSubtitle.text = detail.subtitle
    }

    private fun updateStateError() {
        hideLoading()
    }

    private fun hideLoading() {
        progressProductDetailLoading.visibility = View.GONE
    }

    private fun loadImageHighLight(thumbnail: String) {
        Glide.with(imageProductDetailHighlight.context)
            .load(thumbnail)
            .error(R.drawable.ic_image_detail_placeholder_error)
            .centerInside()
            .listener(object : RequestListener<Drawable> {
                override fun onLoadFailed(
                    e: GlideException?,
                    model: Any?,
                    target: Target<Drawable>?,
                    isFirstResource: Boolean
                ): Boolean {
                    progressProductDetailHighlight.visibility = View.GONE
                    return false
                }

                override fun onResourceReady(
                    resource: Drawable?,
                    model: Any?,
                    target: Target<Drawable>?,
                    dataSource: DataSource?,
                    isFirstResource: Boolean
                ): Boolean {
                    progressProductDetailHighlight.visibility = View.GONE
                    return false
                }

            })
            .into(imageProductDetailHighlight)
    }

    private fun makeHeightProportionalToWidthOfScreen() {
        val displayMetrics = Resources.getSystem().displayMetrics
        val screenHeight = displayMetrics.heightPixels
        val screenWidth = displayMetrics.widthPixels

        val newHeight = imageProductDetailHighlight.measuredWidth * screenWidth / screenHeight

        imageProductDetailHighlight.layoutParams.height = newHeight
    }
 }