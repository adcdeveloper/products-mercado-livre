package com.mercadolivre.core.extensions

import androidx.appcompat.widget.SearchView
import com.mercadolivre.ApplicationTest
import com.mercadolivre.R
import com.mercadolivre.presentation.products.search.view.SearchActivity
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.Robolectric
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(application = ApplicationTest::class, sdk = [28])
class ActivityExtensionsTest {

    private lateinit var activity: SearchActivity
    private lateinit var searchView: SearchView

    @Before
    fun setup() {
        activity = Robolectric
            .buildActivity(SearchActivity::class.java)
            .setup()
            .get()

        searchView = activity.findViewById(R.id.searchView)
    }

    @Test
    fun `should hide keyboard when`() {
        val hidden = activity.hideKeyboard(searchView.windowToken)

        assertEquals(true, hidden)
    }
}