package br.com.danielassumpcao.skymovies

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnit4
import br.com.danielassumpcao.skymovies.ui.activity.MovieActivity
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
@LargeTest
class MovieActivityInstrumentedTest {
    @Rule
    @JvmField
    var mActivityRule = ActivityTestRule(MovieActivity::class.java, true, true)


    @Test
    fun viewsDisplayAsExpected() {
        onView(withId(R.id.mainRV)).check(matches(hasChildCount(0)))
        onView(withId(R.id.swipeLayout)).check(matches(isDisplayed()))
    }
}