package com.example.pizzaorders

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.pizzaorders.databinding.MenuElementBinding


class OrderAdapter : RecyclerView.Adapter<OrderAdapter.ViewHolder>() {
    private var list: List<Order> = listOf()
    private lateinit var binding: MenuElementBinding

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        binding = MenuElementBinding.bind(
            LayoutInflater
                .from(parent.context)
                .inflate(
                    R.layout.menu_element,
                    parent,
                    false
                )
        )
        return ViewHolder(binding.root)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val order: Order = list[holder.adapterPosition]

        Glide.with(binding.imgOrder)
            .load(order.image_url)
            .into(binding.imgOrder)

        binding.countTxt.text = "x" + order.count
        binding.nameOrder.text = order.title
        binding.priceTxt.text = order.price.toString() + "₽"
    }

    override fun getItemCount(): Int = list.size

    fun update(newList: List<Order>) {
        val productDiffUtilCallback = OrdersDiffUtilCallback(list, newList)
        val productDiffResult = DiffUtil.calculateDiff(productDiffUtilCallback)
        list = newList
        productDiffResult.dispatchUpdatesTo(this)

    }
}