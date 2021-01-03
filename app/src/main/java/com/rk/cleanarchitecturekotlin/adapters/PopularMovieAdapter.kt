package com.rk.cleanarchitecturekotlin.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.rk.cleanarchitecturekotlin.listner.ClickListener
import com.rk.cleanarchitecturekotlin.R
import com.rk.cleanarchitecturekotlin.data.local.entity.MovieEntity
import com.rk.cleanarchitecturekotlin.databinding.MovieItemBinding
import com.rk.cleanarchitecturekotlin.utils.Constatnts
import com.squareup.picasso.Picasso

class PopularMovieAdapter(): RecyclerView.Adapter<PopularMovieAdapter.UserViewHolder>() {

    private lateinit var mClickListner: ClickListener
    private lateinit var mPopularMovieList: List<MovieEntity>

    inner class UserViewHolder(val itemViewBinding: MovieItemBinding):RecyclerView.ViewHolder(itemViewBinding.root)

    fun setListofMovies(toList: List<MovieEntity>) {
        mPopularMovieList=toList
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        return UserViewHolder(DataBindingUtil.inflate(LayoutInflater.from(parent.context),
            R.layout.movie_item,parent,false))

    }

    override fun getItemCount(): Int =mPopularMovieList.size

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        Picasso.get()
            .load(Constatnts.IMAGE_ENDPOINT_PREFIX + mPopularMovieList.get(position).poster_path)
            .into(holder.itemViewBinding.popularImage)
        holder.itemViewBinding.root.setOnClickListener {
            mClickListner.onClick(position)
        }
    }

    fun setonclicklistner(clickListner: ClickListener) {
        mClickListner = clickListner
    }


}