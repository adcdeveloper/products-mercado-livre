package com.mercadolivre.presentation.products.list.view

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.mercadolivre.R
import com.mercadolivre.di.injectProductsModule
import com.mercadolivre.presentation.products.list.viewmodel.ProductsViewModel
import com.mercadolivre.presentation.products.search.view.SearchActivity
import kotlinx.android.synthetic.main.activity_products.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class ProductsActivity : AppCompatActivity() {

    init {
        injectProductsModule()
    }

    private val viewModel: ProductsViewModel by viewModel()

    private val startForResult =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result: ActivityResult ->
            if (result.resultCode == RESULT_OK) {
                val query = result.data?.getStringExtra(SearchActivity.EXTRA_SEARCH_QUERY) ?: ""
                viewModel.loadProducts(query)
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_products)

        setSupportActionBar(toolbarProducts)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_search, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.menu_item_search -> {
                goToSearchScreen()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }


    private fun goToSearchScreen() {
        startForResult.launch(Intent(this@ProductsActivity, SearchActivity::class.java))
    }
}