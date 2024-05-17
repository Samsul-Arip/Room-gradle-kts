package com.example.androidtest.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.androidtest.entity.EntityKomoditas
import com.example.androidtest.databinding.ItemTransactionBinding

class EntityKomoditasAdapter : ListAdapter<EntityKomoditas, EntityKomoditasAdapter.EntityKomoditasViewHolder>(EntityKomoditasDiffCallback()) {

    private var onItemClickKomoditas: OnItemClickKomoditas? = null

    fun setOnItemClickKomoditas(onItemClickKomoditas: OnItemClickKomoditas) {
        this.onItemClickKomoditas = onItemClickKomoditas
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EntityKomoditasViewHolder {
        val binding = ItemTransactionBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return EntityKomoditasViewHolder(binding)
    }

    override fun onBindViewHolder(holder: EntityKomoditasViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }

    inner class EntityKomoditasViewHolder(private val binding: ItemTransactionBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(entity: EntityKomoditas) {
            binding.apply {
                tvName.text = entity.nama
                tvJenis.text = entity.jenis
                binding.root.setOnClickListener {
                    onItemClickKomoditas?.onItemClickKomoditas(entity)
                }
            }
        }
    }

    class EntityKomoditasDiffCallback : DiffUtil.ItemCallback<EntityKomoditas>() {
        override fun areItemsTheSame(oldItem: EntityKomoditas, newItem: EntityKomoditas): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: EntityKomoditas, newItem: EntityKomoditas): Boolean {
            return oldItem == newItem
        }
    }

    interface OnItemClickKomoditas {
        fun onItemClickKomoditas(entity: EntityKomoditas)
    }
}