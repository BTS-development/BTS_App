package com.example.bluetoothtemperaturesubmitter

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.bluetoothtemperaturesubmitter.API.RetrofitHelper
import com.example.bluetoothtemperaturesubmitter.DTO.Login
import kotlinx.android.synthetic.main.activity_login.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        btnLogin.setOnClickListener {
            RetrofitHelper().getUserAPI().signIn(IDText.text.toString(), passwordText.text.toString()).enqueue(object : Callback<Login>{
                override fun onResponse(call: Call<Login>, response: Response<Login>) {
                    when (response.code()) {
                        200 -> {
                            val intent = Intent(this@LoginActivity, MainNavigationPager::class.java)
                            intent.putExtra("token", "jwt " + response.body()!!.token)
                            intent.putExtra("pk", response.body()!!.user.pk)
                            startActivity(intent)
                        }
                        400 -> Toast.makeText(this@LoginActivity, "로그인 실패 : 아이디나 비번이 올바르지 않습니다", Toast.LENGTH_LONG).show()
                        500 -> Toast.makeText(this@LoginActivity, "로그인 실패 : 서버 오류", Toast.LENGTH_LONG).show()
                    }
                }
                override fun onFailure(call: Call<Login>, t: Throwable) {
                    Log.d("error",t.toString())
                }

            })
        }

    }
}