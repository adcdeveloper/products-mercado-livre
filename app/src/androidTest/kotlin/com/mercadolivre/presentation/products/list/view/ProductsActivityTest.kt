package com.mercadolivre.presentation.products.list.view

import android.app.Activity
import android.app.Instrumentation
import android.content.Intent
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.intent.Intents
import androidx.test.espresso.intent.Intents.intended
import androidx.test.espresso.intent.Intents.intending
import androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent
import androidx.test.espresso.intent.matcher.IntentMatchers.toPackage
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.mercadolivre.R
import com.mercadolivre.presentation.products.search.view.SearchActivity
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ProductsActivityTest {

    @get:Rule
    var activityRule: ActivityScenarioRule<ProductsActivity> =
        ActivityScenarioRule(ProductsActivity::class.java)

    @Before
    fun before() {
        Intents.init()
    }

    @After
    fun after() {
        Intents.release()
    }

    @Test
    fun shouldReturnTheSearchParamWhenTypedOnTheSearchScreen() {
        onView(withId(R.id.menu_item_search))
            .perform(click())

        intended(hasComponent(SearchActivity::class.java.name))

        val query = "esc"

        onView(withId(androidx.appcompat.R.id.search_src_text))
            .perform(typeText(query))
            .perform(pressImeActionButton())

        val data = Intent()
        data.putExtra(SearchActivity.EXTRA_SEARCH_QUERY, query)
        val result = Instrumentation.ActivityResult(Activity.RESULT_OK, data)

        intending(toPackage(ProductsActivity::class.java.name))
            .respondWith(result)
    }

    @Test
    fun shouldReturnTheSearchParamEmptyWhenNotTypedOnTheSearchScreen() {
        onView(withId(R.id.menu_item_search))
            .perform(click())

        intended(hasComponent(SearchActivity::class.java.name))

        val query = ""

        onView(withId(androidx.appcompat.R.id.search_src_text))
            .perform(pressImeActionButton())

        val data = Intent()
        data.putExtra(SearchActivity.EXTRA_SEARCH_QUERY, query)
        val result = Instrumentation.ActivityResult(Activity.RESULT_CANCELED, data)

        intending(toPackage(ProductsActivity::class.java.name))
            .respondWith(result)
    }
}