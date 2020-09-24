package com.example.bluetoothtemperaturesubmitter.group

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.bluetoothtemperaturesubmitter.API.RetrofitHelper
import com.example.bluetoothtemperaturesubmitter.DTO.Temperature
import com.example.bluetoothtemperaturesubmitter.DTO.User
import com.example.bluetoothtemperaturesubmitter.R
import com.example.bluetoothtemperaturesubmitter.User.Measured_body_tempreture
import kotlinx.android.synthetic.main.activity_group_manage_member.*
import kotlinx.android.synthetic.main.activity_group_manage_notion.*
import kotlinx.android.synthetic.main.activity_group_manage_notion.group_image
import kotlinx.android.synthetic.main.activity_group_manage_notion.notion_group_name
import kotlinx.android.synthetic.main.activity_group_manage_notion.notion_group_people
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

            val token = intent.getStringExtra("token")
            val intent = Intent(this@Group_manage_notion,Measured_body_tempreture::class.java)
            intent.putExtra("token",token)
            startActivity(intent)
        }
        Am_temperature.setOnClickListener {

            val token = intent.getStringExtra("token")
            val intent = Intent(this@Group_manage_notion,Measured_body_tempreture::class.java)
            intent.putExtra("token",token)
            startActivity(intent)
        }
        group_image.setOnClickListener {

            val token = intent.getStringExtra("token")
            val pk = intent.getIntExtra("group_id", 0)
            val group_name = intent.getStringExtra("group_name")
            val code = intent.getStringExtra("group_code")
            val intent = Intent(this@Group_manage_notion,Group_manage_member::class.java)
            intent.putExtra("token",token)
            intent.putExtra("group_id", pk)
            intent.putExtra("group_name", group_name)
            intent.putExtra("group_code", code)
            startActivity(intent)
        }
    }
    fun userTemp(){
        val arrayListMember = ArrayList<Temperature>()
        val token = intent.getStringExtra("token")
        RetrofitHelper().getTemperatureAPI().getGroupTemp(token!!,intent.getIntExtra("group_id",0).toString()).enqueue(object : Callback<List<Temperature>>{
            override fun onResponse(
                call: Call<List<Temperature>>,
                response: Response<List<Temperature>>
            ) {
                if(response.code() == 200) {
                    for (g: Temperature in response.body()!!) {
                        if (g.value >= 37.5) {
                            arrayListMember.add(g)
                        }
                    }
                    notice_list.adapter = GroupNoticeAdapter(this@Group_manage_notion, arrayListMember, token)
                }
                else {
                    Log.d("CODE", response.code().toString())
                }
            }

            override fun onFailure(call: Call<List<Temperature>>, t: Throwable) {
            }

        })
        RetrofitHelper().getGroupAPI().getGroupMember(token,intent.getIntExtra("group_id",0).toString()).enqueue(object : Callback<List<User>>{
            override fun onResponse(call: Call<List<User>>, response: Response<List<User>>) {
                var i = 0
                var text = ""
                if(response.body()!!.size > 3){
                    while (i < 2){
                        text += response.body()!![i].username + ", "
                        i++
                    }
                    text += response.body()!![3]
                    text += "외 "+ (response.body()!!.size-3) + "명"
                } else {
                    while (i < response.body()!!.size){
                        text += response.body()!![i].username
                        if(++i != response.body()!!.size){
                            text += ", "
                        }
                    }
                }
                notion_group_people.text = text
                Log.d("d",response.code().toString())
            }

            override fun onFailure(call: Call<List<User>>, t: Throwable) {
                Log.d("d",t.toString())
            }
        })
    }
}