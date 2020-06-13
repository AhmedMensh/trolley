package com.android.company.app.e_commerce.ui.categories

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.android.company.app.e_commerce.R
import com.android.company.app.e_commerce.data.models.CategoryResponse
import com.android.company.app.e_commerce.utlities.ItemClickListener
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.category_item.view.*


class CategoriesAdapter(private val listener : ItemClickListener<CategoryResponse>) : ListAdapter<CategoryResponse, CategoriesAdapter.ViewHolder>(DiffCallback) {



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        var view = ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.category_item, parent, false))


        return view
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.bind(getItem(position),listener)
    }


    object DiffCallback : DiffUtil.ItemCallback<CategoryResponse>() {
        override fun areItemsTheSame(oldItem: CategoryResponse, newItem: CategoryResponse): Boolean {
            return oldItem.categoryId == newItem.categoryId
        }

        override fun areContentsTheSame(oldItem: CategoryResponse, newItem: CategoryResponse): Boolean {
            return oldItem == newItem
        }

    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

        fun bind(
            category: CategoryResponse,
            listener: ItemClickListener<CategoryResponse>
        ){

            Glide.with(itemView.context).load(category.categoryImage).into(itemView.categoryImgV)
            itemView.categoryNameTV.text = category.categoryTitleEN
            itemView.setOnClickListener { listener.onItemClick(category) }

        }
    }
}