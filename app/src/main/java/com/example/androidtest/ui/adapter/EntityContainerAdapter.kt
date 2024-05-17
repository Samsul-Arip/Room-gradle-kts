package com.example.androidtest.ui.adapter

import android.app.AlertDialog
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.androidtest.databinding.ItemTransactionBinding
import com.example.androidtest.entity.EntityContainer

class EntityContainerAdapter : ListAdapter<EntityContainer, EntityContainerAdapter.EntityContainerViewHolder>(EntityContainerDiffCallback()) {

    private var onItemClickContainer: OnItemClickContainer? = null

    fun setOnItemClickContainer(onItemClickContainer: OnItemClickContainer) {
        this.onItemClickContainer = onItemClickContainer
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EntityContainerViewHolder {
        val binding = ItemTransactionBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return EntityContainerViewHolder(binding)
    }

    override fun onBindViewHolder(holder: EntityContainerViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }

    inner class EntityContainerViewHolder(private val binding: ItemTransactionBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(entity: EntityContainer) {
            binding.apply {
                tvName.text = entity.nama
                tvJenis.text = entity.jenis
                binding.root.setOnClickListener {
                    onItemClickContainer?.onItemclickContainer(entity)
                }
            }
        }


    }



    class EntityContainerDiffCallback : DiffUtil.ItemCallback<EntityContainer>() {
        override fun areItemsTheSame(oldItem: EntityContainer, newItem: EntityContainer): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: EntityContainer, newItem: EntityContainer): Boolean {
            return oldItem == newItem
        }
    }


    interface OnItemClickContainer {
        fun onItemclickContainer(entity: EntityContainer)
    }


}