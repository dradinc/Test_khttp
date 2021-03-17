package com.example.test_khttp

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.annotation.RequiresApi
import com.google.gson.Gson
import okhttp3.*
import okhttp3.HttpUrl.Companion.toHttpUrl
import okhttp3.MediaType.Companion.toMediaTypeOrNull
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

    var token : Int? = null
    val client = OkHttpClient()
    val gson = Gson()
    @Override
    fun run(movieID : String) {

        // Тест GET запроса
        val request = Request.Builder().url("http://cinema.areas.su/movies/$movieID").build()

        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                var result : String = e.toString()
                runOnUiThread {
                    findViewById<TextView>(R.id.Test2_textView).text = result
                }
            }

            override fun onResponse(call: Call, response: Response) {
                var result : String = response.toString()
                runOnUiThread {
                    findViewById<TextView>(R.id.Test2_textView).text = result

                    val responseBody = response.body?.string()
                    val movieInfo : Movie_Info = gson.fromJson(responseBody, Movie_Info::class.java)
                    runOnUiThread{
                        findViewById<TextView>(R.id.Test2_textView1).text = movieInfo.toString()
                    }
                }
            }

        })

        // Тест POST запроса
        val requestBody  = RequestBody.create("application/json; charset=utf-8".toMediaTypeOrNull(), "{\"email\":\"vasya@mail.com\", \"password\":\"qwerty\"}")
        val requestPOST = Request.Builder().url("http://cinema.areas.su/auth/login").post(requestBody).build()
        client.newCall(requestPOST).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                var result : String = e.toString()
                runOnUiThread {
                    findViewById<TextView>(R.id.testPOST).text = result
                }
            }

            override fun onResponse(call: Call, response: Response) {
                var result : String = response.toString()
                runOnUiThread {
                    findViewById<TextView>(R.id.testPOST).text = result

                    val responseBody = response.body?.string()
                    val loginUser : loginUser = gson.fromJson(responseBody, loginUser::class.java)
                    runOnUiThread{
                        token = loginUser.token
                        findViewById<TextView>(R.id.testPost2).text = loginUser.toString()
                        findViewById<Button>(R.id.ButtonToken).visibility = View.VISIBLE
                    }
                }
            }
        })


        /*
        // Тест закрытого запроса с использованием токена
        val request_token = Request.Builder()
            .url("http://cinema.areas.su/user")
            .addHeader("Authorization", "Bearer" + token)
            .build()
        client.newCall(request_token).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                var result : String = e.toString()
                runOnUiThread {
                    findViewById<TextView>(R.id.testToken).text = result
                }
            }

            override fun onResponse(call: Call, response: Response) {
                var result : String = response.toString()
                runOnUiThread {
                    findViewById<TextView>(R.id.testToken).text = result

                    val responseBody = response.body?.string()
                    val dataUsers : dataUsers = gson.fromJson(responseBody, dataUsers::class.java)
                    runOnUiThread {
                        findViewById<TextView>(R.id.testToken2).text = dataUsers.toString()
                    }
                }
            }
        })*/




        /*client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                runOnUiThread {
                    Toast.makeText(this@MainActivity, "Ошибка при запросе", Toast.LENGTH_LONG).show()
                }
            }
            override fun onResponse(call: Call, response: Response) {
                if (response.isSuccessful) {
                    val responseBody = response.body?.string()
                    var gson = Gson()
                    val movieInfo : Movie_Info = gson.fromJson(responseBody, Movie_Info::class.java)
                    runOnUiThread {
                        findViewById<TextView>(R.id.Test2_textView).text = movieInfo.toString()
                    }
                }
            }
        })*/
    }

    fun buttonToken_onClick(view : View) {
        // Тест закрытого запроса с использованием токена
        val request_token = Request.Builder()
            .url("http://cinema.areas.su/user")
            .addHeader("Authorization", "Bearer " + token)
            .build()
        client.newCall(request_token).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                var result : String = e.toString()
                runOnUiThread {
                    findViewById<TextView>(R.id.testToken).text = result
                }
            }

            override fun onResponse(call: Call, response: Response) {
                var result : String = response.toString()
                runOnUiThread {
                    findViewById<TextView>(R.id.testToken).text = result
                }

                    val responseBody = response.body?.string()
                    val dataUsers : List<dataUsers> = gson.fromJson(responseBody, dataUsers::class.java)
                    runOnUiThread {
                        findViewById<TextView>(R.id.testToken2).text = dataUsers.toString()
                    }

            }
        })
    }
}
