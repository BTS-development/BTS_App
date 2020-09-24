package com.example.bluetoothtemperaturesubmitter.group

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.bluetoothtemperaturesubmitter.API.RetrofitHelper
import com.example.bluetoothtemperaturesubmitter.DTO.Groups
import com.example.bluetoothtemperaturesubmitter.DTO.User
import com.example.bluetoothtemperaturesubmitter.R
import kotlinx.android.synthetic.main.activity_group_manage_member.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Group_manage_member : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_group_manage_member)
        manageMemberList()


    }
    fun manageMemberList(){
        val token = intent.getStringExtra("token")
        var manageArrayList = ArrayList<User>()
        Log.d("token", token)
        RetrofitHelper().getGroupAPI().getGroupMember(token,intent.getIntExtra("group_id",0).toString()).enqueue(object : Callback<List<User>>{
            override fun onResponse(call: Call<List<User>>, response: Response<List<User>>) {
                manageArrayList = (response.body() as ArrayList<User>)!!

                Log.d("ID",intent.getIntExtra("group_id",0).toString())

                Group_member.adapter = GroupManageAdapter(this@Group_manage_member,manageArrayList,token)
                Log.d("d",response.code().toString())
            }

            override fun onFailure(call: Call<List<User>>, t: Throwable) {
                Log.d("d",t.toString())
            }
        })
    }
}