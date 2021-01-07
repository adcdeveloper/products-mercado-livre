package com.mercadolivre.presentation.products.detail.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.mercadolivre.R
import com.mercadolivre.di.injectProductDetailModule
import com.mercadolivre.domain.entities.ProductsContent
import com.mercadolivre.presentation.products.detail.viewmodel.ProductDetailViewModel
import kotlinx.android.synthetic.main.activity_product_detail.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class ProductDetailActivity : AppCompatActivity() {

    init {
        injectProductDetailModule()
    }

    companion object {
        const val EXTRA_PRODUCT = "product"
    }

    private val viewModel: ProductDetailViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_product_detail)

        setupToolbar()
        fetchDetail()
    }

    private fun setupToolbar() {
        setSupportActionBar(toolbarProductDetail)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        toolbarProductDetail.setNavigationOnClickListener { finish() }
    }

    private fun fetchDetail() {
        if (intent.hasExtra(EXTRA_PRODUCT)) {
            val product = intent.extras?.get(EXTRA_PRODUCT) as ProductsContent.Product
            viewModel.getDetail(product)
        }
    }
}