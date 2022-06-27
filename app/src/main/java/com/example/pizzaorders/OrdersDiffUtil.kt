package com.example.pizzaorders

import androidx.recyclerview.widget.DiffUtil

class OrdersDiffUtilCallback(oldList: List<Order>, newList: List<Order>) :
    DiffUtil.Callback() {
    private val oldList: List<Order>
    private val newList: List<Order>
    override fun getOldListSize(): Int {
        return oldList.size
    }

    override fun getNewListSize(): Int {
        return newList.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldProduct:Order = oldList[oldItemPosition]
        val newProduct:Order = newList[newItemPosition]
        return oldProduct._id === newProduct._id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldProduct:Order = oldList[oldItemPosition]
        val newProduct:Order = newList[newItemPosition]
        return  oldProduct==newProduct
    }

    init {
        this.oldList = oldList
        this.newList = newList
    }
}