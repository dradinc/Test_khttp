package com.example.test_khttp

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.RequiresApi
import com.google.gson.Gson
import okhttp3.*
import okhttp3.HttpUrl.Companion.toHttpUrl
import okhttp3.RequestBody.Companion.toRequestBody
import org.w3c.dom.Text
import java.io.IOException
import java.net.HttpURLConnection
import java.net.URL


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun Click_SearchMovie (view: View) {
        var movieID : String = findViewById<EditText>(R.id.idMovie).text.toString()
        run(movieID)
    }

    private val client = OkHttpClient()
    fun run(movieID : String) {
        val request = Request.Builder()
                .url("http://cinema.areas.su/movies/$movieID")
                .build()
        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                e.printStackTrace()
            }
            override fun onResponse(call: Call, response: Response) {
                if (response.isSuccessful) {
                    val responseBody = response.body?.string()
                    var gson = Gson()
                    val movieInfo : Movie_Info = gson.fromJson(responseBody, Movie_Info::class.java)
                    val TestString: Movie_Info = gson.fromJson(responseBody, Movie_Info::class.java)
                    runOnUiThread {
                        //findViewById<TextView>(R.id.Test2_textView).text = responseBody
                        findViewById<TextView>(R.id.Test2_textView).text = TestString.toString()
                        findViewById<ImageView>(R.id.MoviePoster).setImageDrawable(movieInfo.getPoster())
                    }
                }
            }
        })
    }
}