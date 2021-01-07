package com.mercadolivre.presentation.products.search.view

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import com.mercadolivre.R
import com.mercadolivre.core.extensions.hideKeyboard
import kotlinx.android.synthetic.main.activity_search.*

class SearchActivity: AppCompatActivity() {

    companion object {
        const val EXTRA_SEARCH_QUERY = "extraSearchQuery"
    }

    private var query: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_search)

        setupToolbar()
        setupSearchView()
    }

    override fun onBackPressed() {
        val hidedKeyboard = hideKeyboard(searchView.windowToken)
        if (hidedKeyboard) return

        finishScreen(true)
    }

    private fun setupToolbar() {
        setSupportActionBar(toolbarSearchProducts)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        toolbarSearchProducts.setNavigationOnClickListener { onBackPressed() }
    }

    private fun setupSearchView() {
        searchView.setOnSearchClickListener {
            hideKeyboard(searchView.windowToken)
            finishScreen(false)
        }

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                finishScreen(false)
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                query = newText ?: ""
                return true
            }

        })
    }

    private fun finishScreen(isCancelledQuery: Boolean) {
        val data = Intent().apply {
            putExtra(EXTRA_SEARCH_QUERY, query)
        }

        val result = if (isCancelledQuery || query.isEmpty()) {
            RESULT_CANCELED
        } else {
            RESULT_OK
        }

        setResult(result, data)
        finish()
    }
}