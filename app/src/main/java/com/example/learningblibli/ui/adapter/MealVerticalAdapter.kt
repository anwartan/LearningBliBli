package com.example.learningblibli.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.learningblibli.R
import com.example.learningblibli.databinding.ItemListMealVerticalBinding
import com.example.learningblibli.domain.model.Meal
import com.example.learningblibli.utils.CustomDiffUtilCallback

class MealVerticalAdapter: RecyclerView.Adapter<MealVerticalAdapter.ViewHolder>() {
    private val listData = ArrayList<Meal>()
    fun setData(newListData: List<Meal>?) {
        if (newListData == null) return
        val customDiffUtilCallback = CustomDiffUtilCallback(this.listData,newListData)
        val diffResult = DiffUtil.calculateDiff(customDiffUtilCallback)
        listData.clear()
        listData.addAll(newListData)

        diffResult.dispatchUpdatesTo(this)
    }

    var onItemClick: ((Meal) -> Unit)? = null
    inner class ViewHolder(itemView:View):RecyclerView.ViewHolder(itemView) {
        private val binding = ItemListMealVerticalBinding.bind(itemView)

        fun bind(data:Meal){
            data.let {
                Glide.with(itemView.context)
                    .load(data.strMealThumb)
                    .into(binding.ivPoster)
                binding.tvTitle.text = data.strMeal
                binding.tvDescription.text=data.strCategory
            }
            binding.root.setOnClickListener {
                onItemClick?.invoke(data)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_list_meal_vertical,parent,false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(listData[position])
    }

    override fun getItemCount(): Int {
        return listData.size
    }
}