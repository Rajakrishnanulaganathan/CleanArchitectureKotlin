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
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.tool_bar.*

class MainActivity : AppCompatActivity() {

    private lateinit var movieFragment: MovieFragment
    protected lateinit var mViewModelProvider: ViewModelProvider.Factory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.statusBarColor = ContextCompat.getColor(this, R.color.colorPrimaryDark)
        val activityMainBinding:ActivityMainBinding=DataBindingUtil.setContentView(this,R.layout.activity_main)
        setSupportActionBar(toolbar)
        toolbar.setNavigationOnClickListener(View.OnClickListener { onBackPressed() })
        toolbar.setTitle("Movies")
        movieFragment = MovieFragment()
        AndroidInjection.inject(this)
        moviefragment()

    }

    fun moviefragment() {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction =
            fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.container_layout, movieFragment).commit()
    }

    override fun onBackPressed() {
        hidebackarrow()
        if (supportFragmentManager.backStackEntryCount !== 0) {
            supportFragmentManager.popBackStack()
        } else {
            super.onBackPressed()
        }
    }
    private fun showFragment(fragment: Fragment) {
        if (fragment is Moviedetailsfragment) {
            showbackarrow()
        } else {
            hidebackarrow()
        }
        makeaddandreplacethefragment(fragment)
    }


    fun showbackarrow() {
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setHomeButtonEnabled(true)
    }

    fun hidebackarrow() {
        supportActionBar!!.setDisplayHomeAsUpEnabled(false)
        supportActionBar!!.setHomeButtonEnabled(false)
    }

    private fun makeaddandreplacethefragment(fragment: Fragment) {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction =
            fragmentManager.beginTransaction()
        fragmentTransaction.addToBackStack("second").replace(R.id.container_layout, fragment)
            .commit()
    }


    fun moviedetailsfragment(id: String) {
        showFragment(newInstance(id))
    }

    companion object {
        const val ARG_NAME = "original_title"


        fun newInstance(name: String): Moviedetailsfragment {
            val fragment = Moviedetailsfragment()

            val bundle = Bundle().apply {
                putString(ARG_NAME, name)
            }

            fragment.arguments = bundle

            return fragment
        }
    }

}
