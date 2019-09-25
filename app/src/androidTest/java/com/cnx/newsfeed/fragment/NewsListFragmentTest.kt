package com.cnx.newsfeed.fragment

import androidx.fragment.app.testing.launchFragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.PositionAssertions.isCompletelyBelow
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnit4
import com.cnx.newsfeed.RecyclerViewItemCountAssertion
import com.cnx.newsfeed.common.PAGE_SIZE
import com.cnx.newsfeed.news.NewsListFragment
import com.cnx.newsfeed.news.NewsViewModel
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito.mock


/**
 *
 * Test when internet connectivity is there and for successful api response
 *
 */
@RunWith(AndroidJUnit4::class)
class NewsListFragmentTest {


    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        assertEquals("com.cnx.newsfeed", appContext.packageName)
    }


    @get:Rule
    public var mActivityRule : ActivityTestRule<SingleFragmentActivity>
            = ActivityTestRule(SingleFragmentActivity::class.java, false,true)

    private lateinit var newsFragment : NewsListFragment

    private lateinit var viewModel: NewsViewModel

    @Before
    fun init() {


        newsFragment = NewsListFragment()
        viewModel = mock(NewsViewModel::class.java)
        newsFragment.viewModelFactory = createViewModelFactory(viewModel)
        mActivityRule.activity.setFragment(newsFragment)
    }




    /** If a device is low on resources, the system might destroy the activity containing your fragment,
     *  requiring  app to recreate the fragment when the user returns to your app. To simulate this situation, */

    @Test fun testEventFragment() {
        val scenario = launchFragment<NewsListFragment>()
        scenario.recreate()
    }

   /** This action simulates a situation where the activity containing your fragment changes its state
     * because it's interrupted by another app or a system action. */

    @Test fun testTransitionsOfFragment() {
        val scenario = launchFragment<NewsListFragment>()
        scenario.moveToState(Lifecycle.State.CREATED)
    }

    /** For the first loading of news we've set pageSize 20, to this test case is to verify this */

    @Test
    fun checkRecyclerViewCount() {

        onView(withId(com.cnx.newsfeed.R.id.rvNewsList)).check(RecyclerViewItemCountAssertion(PAGE_SIZE))
    }


    @Test
    fun checkRecyclerViewAlignment() {
        onView(withId(com.cnx.newsfeed.R.id.rvNewsList)).check(isCompletelyBelow(withId(com.cnx.newsfeed.R.id.appbar)))
    }

    private fun <T : ViewModel> createViewModelFactory(viewModel: T): ViewModelProvider.Factory {

        return object : ViewModelProvider.Factory {

            override fun <T : ViewModel> create(viewModelClass: Class<T>): T {
                if (viewModelClass.isAssignableFrom(viewModel.javaClass)) {
                    @Suppress("UNCHECKED_CAST")
                    return viewModel as T
                }
                throw IllegalArgumentException("Unknown view model class " + viewModelClass)
            }
        }
    }




}
