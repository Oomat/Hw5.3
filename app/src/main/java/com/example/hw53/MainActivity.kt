package com.example.hw53

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.hw53.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    var page = 1
    var keySearch = ""
    var adapter = ImageAdapter(arrayListOf())
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initCLickers()
    }

    private fun initCLickers() {
        binding.btnSearch.setOnClickListener {
            page = 1
            keySearch = binding.etSearch.text.toString()
            adapter.cleanList()
            requestImage()
        }
        binding.btnNext.setOnClickListener {
            if (keySearch != binding.etSearch.text.toString()) {
                adapter.cleanList()
                page = 1
                requestImage()
            } else {
                ++page
                requestImage()
            }
        }

    }

    private fun requestImage() {
        PixaService().api.getImage(binding.etSearch.text.toString(), page = page)
            .enqueue(object : Callback<PixaModel> {
                override fun onResponse(call: Call<PixaModel>, response: Response<PixaModel>) {
                    if (response.isSuccessful) {
                        adapter.addImage(response.body()!!.hits)
                        binding.imageRecycler.adapter = adapter
                    }
                }

                override fun onFailure(call: Call<PixaModel>, t: Throwable) {
                    Log.e("123456", "onFailure: ${t.message}")
                }
            })
    }
}