package com.tare.nestedrv.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.tare.nestedrv.R
import com.tare.nestedrv.databinding.ParentItemsBinding
import com.tare.nestedrv.pojo.entities.ArticleWrapper
import com.tare.nestedrv.ui.HomeViewModel
import com.tare.nestedrv.utils.DiffUtilParent

class ParentAdapter : RecyclerView.Adapter<ParentAdapter.ViewHolder>() {
    private var categoryList: List<ArticleWrapper> = listOf()
    lateinit var homeViewModel: HomeViewModel

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding: ParentItemsBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.parent_items, parent, false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(categoryList[position])
    }

    override fun getItemCount(): Int = categoryList.size

    fun update(updated: List<ArticleWrapper>?) {
        val old = categoryList
        categoryList = updated ?: listOf()
        DiffUtil.calculateDiff(DiffUtilParent(old, categoryList)).dispatchUpdatesTo(this)
    }

    inner class ViewHolder(private val binding: ParentItemsBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(currentCategory: ArticleWrapper) {
            binding.item = currentCategory
            binding.viewModel = homeViewModel
            binding.executePendingBindings()
        }

    }
}