package com.mercadolivre.presentation

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import com.mercadolivre.R
import com.mercadolivre.presentation.products.list.view.ProductsActivity

class SplashActivity : AppCompatActivity() {

    companion object {
        private const val DELAY_TO_STARTS_PRODUCTS_SCREEN = 300L
    }

    private lateinit var handler: Handler
    private lateinit var runnable: Runnable

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_splash)

        postDelayToStartsProductsScreen()
    }

    override fun onDestroy() {
        super.onDestroy()

        handler.removeCallbacks(runnable)
    }

    private fun postDelayToStartsProductsScreen() {
        handler = Handler(Looper.getMainLooper())
        runnable = Runnable {
            startActivity(Intent(this@SplashActivity, ProductsActivity::class.java))
            finish()
        }
        handler.postDelayed(runnable, DELAY_TO_STARTS_PRODUCTS_SCREEN)
    }

}