package com.example.bluetoothtemperaturesubmitter.group

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.bluetoothtemperaturesubmitter.API.RetrofitHelper
import com.example.bluetoothtemperaturesubmitter.DTO.JoinGroup
import com.example.bluetoothtemperaturesubmitter.R
import kotlinx.android.synthetic.main.activity_group_join.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Group_join : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_group_join)

        val token : String = intent.getStringExtra("token")!!

        Group_Join.setOnClickListener{
            RetrofitHelper().getGroupAPI().joinGroup(token, Group_ID.text.toString()).enqueue(object : Callback<JoinGroup>{
                override fun onFailure(call: Call<JoinGroup>, t: Throwable) {
                    Log.d("TAG", t.toString())
                }

                override fun onResponse(call: Call<JoinGroup>, response: Response<JoinGroup>) {
                    if (response.isSuccessful){
                        Log.d("CODE", response.code().toString())
                        Toast.makeText(this@Group_join, "그룹에 가입 되었습니다.", Toast.LENGTH_LONG).show()
                        finish()
                    }
                }

            })
        }

    }
}