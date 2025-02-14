package com.example.cryptocurrencyapp

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.cryptocurrencyapp.databinding.ActivityMainBinding

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    // call recylcer view and Adapter
    private lateinit var recyclerView: RecyclerView
    private lateinit var cryptoAdapter: RecyclerViewAdapter

    private lateinit var binding : ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // use binding to inflate the layout
        binding =ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        // get the recycler view and set the layout manager
        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        // function of fetch data
        fetchCryptos()
    }

    private fun fetchCryptos() {
        // use retrofit to get the data from the api
        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.coinlore.net/api/") // Replace with actual base URL
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        // create an instance of the api service
        val apiService = retrofit.create(ApiService::class.java)
        // make a call to the api
        apiService.getCryptoData().enqueue(object : Callback<ApiResponse> {
            override fun onResponse(call: Call<ApiResponse>, response: Response<ApiResponse>) {
                if (response.isSuccessful) {
                    val cryptoList = response.body()?.data ?: emptyList()
                    cryptoAdapter = RecyclerViewAdapter(cryptoList)
                    recyclerView.adapter = cryptoAdapter
                }
            }

            override fun onFailure(call: Call<ApiResponse>, t: Throwable) {
                Toast.makeText(this@MainActivity, "Failed to load data", Toast.LENGTH_SHORT).show()
            }
        })
    }
}