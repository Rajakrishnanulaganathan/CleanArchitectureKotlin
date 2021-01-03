package com.rk.cleanarchitecturekotlin.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.rk.cleanarchitecturekotlin.listner.ClickListener
import com.rk.cleanarchitecturekotlin.adapters.PopularMovieAdapter
import com.rk.cleanarchitecturekotlin.R
import com.rk.cleanarchitecturekotlin.viewmodel.MovieViewModel
import com.rk.cleanarchitecturekotlin.data.local.entity.MovieEntity
import com.rk.cleanarchitecturekotlin.databinding.FragmentMovieBinding
import com.rk.cleanarchitecturekotlin.utils.State
import com.rk.cleanarchitecturekotlin.utils.showToast
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.fragment_movie.*
import javax.inject.Inject

class MovieFragment :Fragment() , ClickListener {

    private lateinit var movieist: List<MovieEntity>
    @Inject
    lateinit var mViewModelProvider: ViewModelProvider.Factory
    @Inject
    lateinit var popularMovieViewmodel: MovieViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidSupportInjection.inject(this)
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val fragmentBinding:FragmentMovieBinding=DataBindingUtil.inflate(inflater,R.layout.fragment_movie,container,false)
        init()
        return fragmentBinding.root
    }

    private fun init() {
        val popularMovieAdapter = PopularMovieAdapter()
        popularMovieViewmodel.getPosts()
        popularMovieViewmodel.postsLiveData.observe(getViewLifecycleOwner(), Observer { state ->
            when (state) {
                is State.Success -> {
                    if (state.data.isNotEmpty()) {
                        popularMovieAdapter.setListofMovies(state.data.toList())
                        movieist=state.data.toList()
                        popular_movie_recyclerview.apply {
                            layoutManager = GridLayoutManager(context,2)
                            adapter = popularMovieAdapter
                        }
                        popularMovieAdapter.setonclicklistner(this)
                    }
                }
                is State.Error -> {
                    showToast(state.message)
                }
            }
        })

    }

    override fun onClick(position: Int) {
        position?.let {
            movieist.get(position).original_title?.let { it1 ->
                (activity as MainActivity).movieDetailsFragment(
                    it1
                )
            }
        }
    }

}