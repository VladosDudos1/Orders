package com.example.pizzaorders

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.pizzaorders.databinding.MenuElementBinding

class OrderAdapter(var list: List<Order>) : RecyclerView.Adapter<OrderAdapter.ViewHolder>() {

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
        Glide.with(binding.imgOrder)
            .load(list[position].image)
            .into(binding.imgOrder)

        binding.countTxt.text = "x" + list[position].count
        binding.nameOrder.text = list[position].name
        binding.priceTxt.text = list[position].price.toString()
    }

    override fun getItemCount(): Int = list.size
}