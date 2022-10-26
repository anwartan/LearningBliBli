package com.example.learningblibli.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.learningblibli.R
import com.example.learningblibli.databinding.ItemListMealBinding
import com.example.learningblibli.domain.model.Meal
import com.example.learningblibli.utils.CustomDiffUtilCallback

class MealAdapter:RecyclerView.Adapter<MealAdapter.ViewHolder>() {


    var onItemClick: ((Meal) -> Unit)? = null
    private val listData = ArrayList<Meal>()

    fun setData(newListData: List<Meal>?) {
        if (newListData == null) return
        val customDiffUtilCallback = CustomDiffUtilCallback(this.listData,newListData)
        val diffResult = DiffUtil.calculateDiff(customDiffUtilCallback)
        listData.clear()
        listData.addAll(newListData)

        diffResult.dispatchUpdatesTo(this)
    }

    inner class ViewHolder(itemView:View): RecyclerView.ViewHolder(itemView) {
        private val binding = ItemListMealBinding.bind(itemView)
        fun bind(data: Meal) {
            with(binding) {
                data.let {
                    Glide.with(itemView.context)
                        .load(it.strMealThumb)
                        .into(ivPoster)
                    tvTitle.text=it.strMeal
                }

            }
            binding.root.setOnClickListener {
                onItemClick?.invoke(data)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
       return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_list_meal,parent,false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = listData[position]
        holder.bind(data)

    }

    override fun getItemCount(): Int {
        return listData.size
    }
}