package com.udb.taller2DSA.ui.cart

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.udb.taller2DSA.databinding.CartItemBinding
import com.udb.taller2DSA.model.CartModel

class CartAdapter : ListAdapter<CartModel, CartAdapter.ItemViewHolder>(DiffCallback) {


    class ItemViewHolder(private var binding: CartItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: CartModel) {
            binding.cart = item
            binding.executePendingBindings()
        }
    }

    companion object DiffCallback : DiffUtil.ItemCallback<CartModel>() {
        override fun areItemsTheSame(oldItem: CartModel, newItem: CartModel): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: CartModel, newItem: CartModel): Boolean {
            return oldItem.id == newItem.id
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ItemViewHolder {
        return ItemViewHolder(CartItemBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }
}