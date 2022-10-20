package com.example.learningblibli.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.learningblibli.R
import com.example.learningblibli.data.source.remote.network.ApiConfig
import com.example.learningblibli.databinding.ItemListMovieBinding
import com.example.learningblibli.domain.model.Movie
import com.example.learningblibli.utils.CustomDiffUtilCallback

class MovieAdapter:RecyclerView.Adapter<MovieAdapter.ViewHolder>() {


    var onItemClick: ((Movie) -> Unit)? = null
    private val listData = ArrayList<Movie>()

    fun setData(newListData: List<Movie>?) {
        if (newListData == null) return
        val customDiffUtilCallback = CustomDiffUtilCallback(this.listData,newListData)
        val diffResult = DiffUtil.calculateDiff(customDiffUtilCallback)
        listData.clear()
        listData.addAll(newListData)

        diffResult.dispatchUpdatesTo(this)
    }

    inner class ViewHolder(itemView:View): RecyclerView.ViewHolder(itemView) {
        private val binding = ItemListMovieBinding.bind(itemView)
        fun bind(data: Movie) {
            with(binding) {
                Glide.with(itemView.context)
                    .load(ApiConfig.BASE_IMAGE_URL+data.posterPath)
                    .into(ivPoster)
            }
        }

        init {
            binding.root.setOnClickListener {
                onItemClick?.invoke(listData[adapterPosition])
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
       return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_list_movie,parent,false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = listData[position]
        holder.bind(data)
    }

    override fun getItemCount(): Int {
        return listData.size
    }
}