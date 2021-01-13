package br.com.danielassumpcao.skymovies

import android.content.Intent
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnit4
import br.com.danielassumpcao.skymovies.models.Image
import br.com.danielassumpcao.skymovies.models.Movie
import br.com.danielassumpcao.skymovies.models.PlotOutline
import br.com.danielassumpcao.skymovies.models.Title
import br.com.danielassumpcao.skymovies.ui.activity.DetailActivity
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
@LargeTest
class DetailActivityInstrumentedTest {

    @Rule
    @JvmField
    var mActivityRule = ActivityTestRule(DetailActivity::class.java, true, true)


    @Test
    fun viewsDisplayAsExpected() {
        mActivityRule.launchActivity(getActivityIntent())


        onView(withId(R.id.backButton)).check(matches(isDisplayed()))
        onView(withId(R.id.titleTV)).check(matches(isDisplayed()))
        onView(withId(R.id.releaseYearTV)).check(matches(isDisplayed()))
        onView(withId(R.id.durationTV)).check(matches(isDisplayed()))
        onView(withId(R.id.overviewTV)).check(matches(isDisplayed()))
        onView(withId(R.id.genreTV)).check(matches(isDisplayed()))
        onView(withId(R.id.coverIV)).check(matches(isDisplayed()))

    }

    fun getActivityIntent(): Intent {
        val intent = Intent()
        intent.putExtra("EXTRA_MOVIE", generateMovieMock())
        return intent
    }

    fun generateMovieMock(): Movie {
        val imageTest = Image("999", "999", "https://cutt.ly/gjbNMXd")
        val titleTest = Title("MovieTest", "92", imageTest)
        val plotOutlineTest = PlotOutline("PlotOutlineTest")

        return Movie("/title/tt7126948/", titleTest, arrayListOf("Drama"), "2019-11-23", plotOutlineTest)
    }
}