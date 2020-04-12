package com.rk.cleanarchitecturekotlin.ui

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.rk.cleanarchitecturekotlin.R
import com.rk.cleanarchitecturekotlin.viewmodel.MovieViewModel
import com.rk.cleanarchitecturekotlin.data.local.entity.MovieEntity
import com.rk.cleanarchitecturekotlin.databinding.ActivityMovieDetailsBinding
import com.rk.cleanarchitecturekotlin.utils.Constatnts
import com.squareup.picasso.Picasso
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject

class Moviedetailsfragment :Fragment(){


    private lateinit var isMygetValue: String
    @Inject
    lateinit var mViewModelProvider: ViewModelProvider.Factory

    @Inject
    lateinit var popularMovieViewmodel: MovieViewModel

    lateinit var fragmentBinding:ActivityMovieDetailsBinding


    override fun onAttach(context: Context) {
        super.onAttach(context)
        arguments?.getString("original_title")?.let {
            isMygetValue = it
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {

        AndroidSupportInjection.inject(this)
        popularMovieViewmodel =
            ViewModelProviders.of(this, mViewModelProvider).get<MovieViewModel>(
                MovieViewModel::class.java
            )
        super.onCreate(savedInstanceState)


}

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

         fragmentBinding =
            DataBindingUtil.inflate(inflater, R.layout.activity_movie_details,container,false)
       getdetails(isMygetValue)
        return fragmentBinding.root
    }

    private fun getdetails(value: String) {
        Log.d("title",value)
        popularMovieViewmodel.getMoviesDetails(value).observe(getViewLifecycleOwner(), Observer {
            fragmentBinding.movie=it
            addMovieDetails(it)


        })

    }
    private fun addMovieDetails(movieEntity: MovieEntity) {
        Picasso.get().load(Constatnts.IMAGE_ENDPOINT_PREFIX + movieEntity.poster_path)
            .into(fragmentBinding.imageViewCover)
    }


}