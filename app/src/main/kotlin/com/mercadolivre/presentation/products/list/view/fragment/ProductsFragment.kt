package com.mercadolivre.presentation.products.list.view.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import com.mercadolivre.R
import com.mercadolivre.domain.entities.ProductsContent
import com.mercadolivre.presentation.products.detail.view.ProductDetailActivity
import com.mercadolivre.presentation.products.list.state.ProductsState
import com.mercadolivre.presentation.products.list.view.adapter.ProductsAdapter
import com.mercadolivre.presentation.products.list.viewmodel.ProductsViewModel
import kotlinx.android.synthetic.main.fragment_products.*
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class ProductsFragment : Fragment() {

    private val viewModel: ProductsViewModel by sharedViewModel()

    private lateinit var adapter: ProductsAdapter

    init {
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setupViewModel()
        return inflater.inflate(R.layout.fragment_products, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setupRecyclerView()
    }

    private fun setupRecyclerView() {
        adapter = ProductsAdapter { product -> goToProductDetailScreen(product) }
        recyclerProducts.adapter = adapter

        val divider = DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL)
        val drawable = ContextCompat.getDrawable(requireContext(), R.drawable.divider_product)
        drawable?.let {
            divider.setDrawable(it)
        }

        recyclerProducts.addItemDecoration(divider)
    }

    private fun setupViewModel() {
        viewModel.productsState.observe(viewLifecycleOwner, { state ->
            when (state) {
                is ProductsState.ShowLoading -> updateStateLoading()
                is ProductsState.ShowProducts -> updateStateProducts(state.products)
                is ProductsState.ShowEmptyQuery -> updateStateEmpty(true)
                is ProductsState.ShowError -> updateStateError(true)
            }
        })
    }

    private fun updateStateLoading() {
        progressLoading.visibility = View.VISIBLE

        adapter.addProducts(arrayListOf())
        updateStateEmpty(false)
        updateStateError(false)
    }

    private fun updateStateProducts(products: List<ProductsContent.Product>){
        progressLoading.visibility = View.GONE
        adapter.addProducts(products)
    }

    private fun updateStateEmpty(show: Boolean) {
//        progressLoading.visibility = View.GONE
    }

    private fun updateStateError(show: Boolean) {
//        progressLoading.visibility = View.GONE
    }

    private fun goToProductDetailScreen(product: ProductsContent.Product) {
        val intent = Intent(requireContext(), ProductDetailActivity::class.java)
            .apply { putExtra(ProductDetailActivity.EXTRA_PRODUCT, product) }
        startActivity(intent)
    }
}