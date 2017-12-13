package com.kram.vlad.cryptocurrency.activitys

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.kram.vlad.cryptocurrency.R
import com.kram.vlad.cryptocurrency.adapters.RecyclerViewAdapter
import com.kram.vlad.cryptocurrency.REST.RetrofitGetInterface
import com.kram.vlad.cryptocurrency.models.ResponseItem
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        doRequest()
    }

    private fun doRequest(){
        val apiService = RetrofitGetInterface.create()
        apiService.getCryptocurrency()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe({
                    result -> arrayListInit(result)
                }, { error ->
                    error.printStackTrace()
                })
    }

    private fun arrayListInit(result : List<ResponseItem>){
        recyclerView.adapter = RecyclerViewAdapter(result, resources)
        recyclerView.layoutManager = LinearLayoutManager(this)
    }
}

