package com.example.bluetoothtemperaturesubmitter.group

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.bluetoothtemperaturesubmitter.API.RetrofitHelper
import com.example.bluetoothtemperaturesubmitter.DTO.Temperature
import com.example.bluetoothtemperaturesubmitter.R
import com.example.bluetoothtemperaturesubmitter.User.Measured_body_tempreture
import kotlinx.android.synthetic.main.activity_group_manage_notion.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Group_manage_notion : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_group_manage_notion)
        getNotice()
        userTemp()
        getNoticeIntent()
    }

    fun getNotice(){
        notion_group_name.text = intent.getStringExtra("group_name")
    }
    fun getNoticeIntent(){
        Afternoon_temperature.setOnClickListener {
            val intent = Intent(this@Group_manage_notion,Measured_body_tempreture::class.java)
            val token = intent.getStringExtra("token")
            intent.putExtra("token",token)
            startActivity(intent)
        }
        Am_temperature.setOnClickListener {
            val intent = Intent(this@Group_manage_notion,Measured_body_tempreture::class.java)
            val token = intent.getStringExtra("token")
            intent.putExtra("token",token)
            startActivity(intent)
        }
    }
    fun userTemp(){
        val arrayList = ArrayList<Temperature>()
        val token = intent.getStringExtra("token")
        RetrofitHelper().getTemperatureAPI().getGroupTemp(token!!,intent.getIntExtra("group_id",0).toString()).enqueue(object : Callback<List<Temperature>>{
            override fun onResponse(
                call: Call<List<Temperature>>,
                response: Response<List<Temperature>>
            ) {
                if(response.code() == 200) {
                    for (g: Temperature in response.body()!!) {
                        if (g.value >= 37.5) {
                            arrayList.add(g)
                        }
                    }
                    notice_list.adapter = GroupNoticeAdapter(this@Group_manage_notion, arrayList, token)
                }
                else {
                    Log.d("CODE", response.code().toString())
                }
            }

            override fun onFailure(call: Call<List<Temperature>>, t: Throwable) {
            }

        })
    }
}