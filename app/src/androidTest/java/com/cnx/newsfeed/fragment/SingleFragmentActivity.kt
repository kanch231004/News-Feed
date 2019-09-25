package com.cnx.newsfeed.fragment

import android.os.Bundle
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.cnx.newsfeed.R

/**
 * Used for testing fragments inside a fake activity.
 */
class SingleFragmentActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        val content = FrameLayout(this).apply {
            layoutParams = FrameLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
            )
            id = R.id.nav_fragment
        }
        setContentView(content)
    }

    fun setFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .add(R.id.nav_fragment, fragment, "TEST")
            .commit()
    }

    fun replaceFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.nav_fragment, fragment)
            .commit()
    }
}
