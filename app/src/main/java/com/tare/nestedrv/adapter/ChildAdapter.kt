package com.tare.nestedrv.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.tare.nestedrv.R
import com.tare.nestedrv.databinding.ChildItemsBinding
import com.tare.nestedrv.pojo.entities.Article
import com.tare.nestedrv.ui.HomeViewModel
import com.tare.nestedrv.utils.DiffUtilChild

class ChildAdapter : RecyclerView.Adapter<ChildAdapter.ViewHolder>() {
    private var itemList: List<Article> = listOf()
    lateinit var homeViewModel: HomeViewModel

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding: ChildItemsBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.child_items, parent, false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(itemList[position])
    }

    override fun getItemCount(): Int = itemList.size

    fun update(updated: List<Article>?) {
        val old = itemList
        itemList = updated ?: listOf()
        DiffUtil.calculateDiff(DiffUtilChild(old, itemList)).dispatchUpdatesTo(this)
    }

    inner class ViewHolder(private val binding: ChildItemsBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(currentItem: Article) {
            binding.item = currentItem
            binding.viewModel = homeViewModel
            binding.executePendingBindings()
        }
    }
}