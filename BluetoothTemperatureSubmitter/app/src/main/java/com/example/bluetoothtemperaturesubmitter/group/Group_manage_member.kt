package com.example.bluetoothtemperaturesubmitter.group

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.bluetoothtemperaturesubmitter.API.RetrofitHelper
import com.example.bluetoothtemperaturesubmitter.DTO.Groups
import com.example.bluetoothtemperaturesubmitter.DTO.User
import com.example.bluetoothtemperaturesubmitter.DTO.UserInfo
import com.example.bluetoothtemperaturesubmitter.R
import kotlinx.android.synthetic.main.activity_group_manage_member.*
import kotlinx.android.synthetic.main.group_management.view.*
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
        member_group_name.text = intent.getStringExtra("group_name")
        Log.d("token", token)
        Group_Code.text = intent.getStringExtra("group_code")
        RetrofitHelper().getGroupAPI().getGroupMember(token,intent.getIntExtra("group_id",0).toString()).enqueue(object : Callback<List<User>>{
            override fun onResponse(call: Call<List<User>>, response: Response<List<User>>) {
                manageArrayList = (response.body() as ArrayList<User>)!!

                Log.d("ID",intent.getIntExtra("group_id",0).toString())

                Group_member.adapter = GroupManageAdapter(this@Group_manage_member,manageArrayList,token)

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
                member_group_people.text = text
                Log.d("d",response.code().toString())
            }

            override fun onFailure(call: Call<List<User>>, t: Throwable) {
                Log.d("d",t.toString())
            }
        })
        RetrofitHelper().getUserAPI().getUser(token, intent.getIntExtra("group_owner",0).toString()).enqueue(object : Callback<UserInfo>{
            override fun onFailure(call: Call<UserInfo>, t: Throwable) {

            }

            override fun onResponse(call: Call<UserInfo>, response: Response<UserInfo>) {
                if (response.isSuccessful){
                    Grout_reader_name.text = response.body()!!.username
                }
            }

        })
    }
}