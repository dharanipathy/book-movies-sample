package com.example.bookmovies.view

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.bookmovies.R
import com.example.bookmovies.model.remote.retrofit.MovieResponse
import com.example.bookmovies.model.remote.retrofit.RetrofitBuilder
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        fetchMovies()
    }

    private fun fetchMovies() {
        val request = RetrofitBuilder.buildService()
        val call = request.getMovies(API_KEY)
        call.enqueue(object : Callback<MovieResponse>{
            override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
                textResultView.text = "error"
                Toast.makeText(this@MainActivity, "error!!!", Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(call: Call<MovieResponse>, response: Response<MovieResponse>) {
                Toast.makeText(this@MainActivity, "success " + response.code(), Toast.LENGTH_SHORT).show()
//                if(response.isSuccessful && response.body() != null){
                    val movies = response.body()!!.results
                    textResultView.text = movies.title
                    Toast.makeText(this@MainActivity, movies.title, Toast.LENGTH_SHORT).show()
//                }
            }

        })
    }

    companion object{
        private const val API_KEY = "b82c5a62017c2768729a80fc2cd70f94"
    }
}