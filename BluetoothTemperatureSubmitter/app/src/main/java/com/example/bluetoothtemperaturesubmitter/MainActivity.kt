package com.example.bluetoothtemperaturesubmitter

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.bluetoothtemperaturesubmitter.API.RetrofitHelper
import com.example.bluetoothtemperaturesubmitter.DTO.Login
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    var userId = ""
    var pwd = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        getData()

        RetrofitHelper().getUserAPI().signIn(userId,pwd).enqueue(object : Callback<Login>{
            override fun onFailure(call: Call<Login>, t: Throwable) {}

            override fun onResponse(call: Call<Login>, response: Response<Login>) {
                if(response.isSuccessful){
                    if(response.code() == 200){
                        val intent = Intent(this@MainActivity, MainNavigationPager::class.java)
                        intent.putExtra("token", "jwt " + response.body()!!.token)
                        intent.putExtra("pk", response.body()!!.user.pk)
                        startActivity(intent)
                        finish()
                    }
                }
            }

        })

        btnSignIn.setOnClickListener {
            val intent = Intent(this@MainActivity, LoginActivity::class.java)
            startActivity(intent)
        }


        btnSignUp.setOnClickListener {
            val intent = Intent(this@MainActivity, SignupActivity::class.java)
            startActivity(intent)
        }
    }
    private fun getData(){
        val pref = getSharedPreferences("user", Activity.MODE_PRIVATE)
        userId = pref.getString("id","").toString()
        pwd = pref.getString("pwd","").toString()
    }
}