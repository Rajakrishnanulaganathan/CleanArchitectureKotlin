package com.rk.cleanarchitecturekotlin.ui

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.rk.cleanarchitecturekotlin.R
import com.rk.cleanarchitecturekotlin.databinding.ActivityMainBinding
import com.rk.cleanarchitecturekotlin.utils.Constatnts
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.tool_bar.*

class MainActivity : AppCompatActivity() {

    private lateinit var movieFragment: MovieFragment
    protected lateinit var mViewModelProvider: ViewModelProvider.Factory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.statusBarColor = ContextCompat.getColor(this, R.color.colorPrimaryDark)
        val activityMainBinding: ActivityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        setSupportActionBar(toolbar)
        toolbar.setNavigationOnClickListener(View.OnClickListener { onBackPressed() })
        toolbar.title = Constatnts.MOVIES
        movieFragment = MovieFragment()
        AndroidInjection.inject(this)
        movieFragment()

    }

    private fun movieFragment() {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction =
            fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.container_layout, movieFragment).commit()
    }

    override fun onBackPressed() {
        hideBackArrow()
        if (supportFragmentManager.backStackEntryCount !== 0) {
            supportFragmentManager.popBackStack()
        } else {
            super.onBackPressed()
        }
    }

    private fun showFragment(fragment: Fragment) {
        if (fragment is MovieDetailsFragment) {
            showBackArrow()
        } else {
            hideBackArrow()
        }
        addAndReplaceFragment(fragment)
    }


    private fun showBackArrow() {
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setHomeButtonEnabled(true)
    }

    private fun hideBackArrow() {
        supportActionBar!!.setDisplayHomeAsUpEnabled(false)
        supportActionBar!!.setHomeButtonEnabled(false)
    }

    private fun addAndReplaceFragment(fragment: Fragment) {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.addToBackStack("second").replace(R.id.container_layout, fragment).commit()
    }


    fun movieDetailsFragment(id: String) {
        showFragment(newInstance(id))
    }

    companion object {
        fun newInstance(name: String): MovieDetailsFragment {
            val fragment = MovieDetailsFragment()
            val bundle = Bundle().apply {
                putString(Constatnts.ORIGINAL_TITLE, name)
            }
            fragment.arguments = bundle
            return fragment
        }
    }

}
