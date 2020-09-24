package com.example.bluetoothtemperaturesubmitter.group

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import com.example.bluetoothtemperaturesubmitter.API.RetrofitHelper
import com.example.bluetoothtemperaturesubmitter.DTO.Groups
import com.example.bluetoothtemperaturesubmitter.DTO.JoinGroup
import com.example.bluetoothtemperaturesubmitter.DTO.UserInfo
import com.example.bluetoothtemperaturesubmitter.Fragment.GroupFragment
import com.example.bluetoothtemperaturesubmitter.MainActivity
import com.example.bluetoothtemperaturesubmitter.MainNavigationPager
import com.example.bluetoothtemperaturesubmitter.R
import com.example.bluetoothtemperaturesubmitter.User.Measured_body_tempreture
import kotlinx.android.synthetic.main.activity_group_create.*
import kotlinx.android.synthetic.main.activity_group_join.*
import kotlinx.android.synthetic.main.activity_group_join.Group_Join
import kotlinx.android.synthetic.main.fragment_main__user.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.reflect.Array.getInt

class Group_create : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_group_create)

        userNameGroup()
        GroupName_Next()
    }
    fun GroupName_Next(){
        val token = intent.getStringExtra("token")


        RetrofitHelper().getUserAPI().getUser(token, intent.getIntExtra("pk",0).toString()).enqueue(object : Callback<UserInfo>{
            override fun onFailure(call: Call<UserInfo>, t: Throwable) {
                Log.d("ERROR", t.toString())
            }

            override fun onResponse(call: Call<UserInfo>, response: Response<UserInfo>) {
                if(response.isSuccessful){
                    if(response.body() != null){
                        Group_my_email.text = response.body()!!.email
                        Group_my_name.text = response.body()!!.username
                    }else {
                        Log.d("ERROR", response.code().toString())
                    }
                }
                else {
                    Log.d("ERROR", response.code().toString())
                }
            }

        })


        Group_Join.setOnClickListener {
            val group_user_name = Group_ID_create.text.toString()

            Log.d("TAG", token)
            Log.d("PK", group_user_name)

            RetrofitHelper().getGroupAPI().createGroup(token,group_user_name).enqueue(object : Callback<Groups>{
                override fun onResponse(call: Call<Groups>, response: Response<Groups>) {
                    when (response.code()) {
                        200 -> {
                            Toast.makeText(this@Group_create , "그룹 생성 성공", Toast.LENGTH_SHORT).show()
                            val intent = Intent(this@Group_create, MainNavigationPager::class.java)
                            intent.putExtra("token", token)
                            intent.putExtra("pk", response.body()!!.id)
                            startActivity(intent)
                            finish()
                        }
                        400 -> {
                            Toast.makeText(this@Group_create, "그룹생성 실패: 그룹 생성 오류", Toast.LENGTH_SHORT).show()
                        }
                        500 -> {
                            Toast.makeText(this@Group_create, "그룹 생성실패: 서버 오류", Toast.LENGTH_SHORT).show()
                        }
                        else -> {
                            Toast.makeText(this@Group_create, "그룹 생성 실패: 이유 불명" + response.code(), Toast.LENGTH_SHORT).show()
                        }
                    }
                }

                override fun onFailure(call: Call<Groups>, t: Throwable) {
                    Log.d("TAG", t.toString())
                }

            })
        }

    }
    fun userNameGroup(){
        val token = intent.getStringExtra("token")
        val pk = intent.getIntExtra("pk",0)

        Log.d("TAG", token)
        Log.d("PK", pk.toString())

        if (token != null && pk != 0) {
            RetrofitHelper().getUserAPI().getUser(token, pk.toString()).enqueue(object : Callback<UserInfo> {
                override fun onResponse(call: Call<UserInfo>, response: Response<UserInfo>) {
                    if (response.isSuccessful) {
                        Log.d("TAG", "GO")
                        if (response.body() != null) {
                            Log.d("TAG", "GO")
                            Group_my_email.text = response.body()!!.email
                            Group_my_name.text = response.body()!!.username
                        }
                    }


                }

                override fun onFailure(call: Call<UserInfo>, t: Throwable) {
                    Log.d("ERROR", t.toString())
                }

            })
        }
    }
}