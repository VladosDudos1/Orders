package com.example.pizzaorders

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.example.pizzaorders.databinding.ActivityMainBinding
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var listOrders = mutableListOf<Order>()
    private var timerDisposable: Disposable? = null
    private var ordersDisposable: Disposable? = null
    private lateinit var orderAdapter: OrderAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.rvInProcess.layoutManager = GridLayoutManager(this, 3)
        setAdapter()

        getOrders()
    }

    override fun onResume() {
        super.onResume()

        makeRequest()
    }

    override fun onStop() {
        super.onStop()

        timerDisposable.also { it!!.dispose() }
        ordersDisposable.also { it!!.dispose() }
    }

    private fun setAdapter() {
        orderAdapter = OrderAdapter()
        binding.rvInProcess.adapter = orderAdapter
    }

    private fun makeRequest() {
        timerDisposable = Observable
            .timer(5, TimeUnit.SECONDS)
            .repeat()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                getOrders()

                println("fuck")
            }
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun getOrders() {
        ordersDisposable = App.dm.api
            .getOrders()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                for (i in it)
                    for (j in i.products) {
                        if (listOrders.firstOrNull { d -> d._id == j._id } == null)
                            listOrders.add(j)
                    }
                orderAdapter.update(listOrders)
            }, {
                Toast.makeText(this, "Something wrong", Toast.LENGTH_SHORT).show()
            })
    }
}