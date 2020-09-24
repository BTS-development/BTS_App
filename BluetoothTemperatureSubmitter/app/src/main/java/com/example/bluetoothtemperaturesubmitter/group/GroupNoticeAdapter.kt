package com.example.bluetoothtemperaturesubmitter.group

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.example.bluetoothtemperaturesubmitter.API.RetrofitHelper
import com.example.bluetoothtemperaturesubmitter.DTO.Temperature
import com.example.bluetoothtemperaturesubmitter.DTO.User
import com.example.bluetoothtemperaturesubmitter.DTO.UserInfo
import com.example.bluetoothtemperaturesubmitter.R
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class GroupNoticeAdapter (context: Context, noticeList : List<Temperature>,token : String) : BaseAdapter(){
    val mToken = token
    private val mContext : Context = context
    private val NoticeData = noticeList

    override fun getCount(): Int {
        return NoticeData.size
    }

    override fun getItem(position: Int): Any {
        return NoticeData[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    @SuppressLint("SetTextI18n")
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var temp = ""
        val layoutInflater = LayoutInflater.from(mContext)
        val view = layoutInflater.inflate(R.layout.textview_group,parent,false)
        val noticeNameList = view.findViewById<TextView>(R.id.notice_text)

        RetrofitHelper().getUserAPI().getUser(mToken, NoticeData[position].owner.toString()).enqueue(object : Callback<UserInfo>{
            override fun onResponse(call: Call<UserInfo>, response: Response<UserInfo>) {
                Log.d("CODE", response.code().toString())
                Log.d("NAME", "i"+response.body()!!.username)
                temp = response.body()!!.username

                noticeNameList.text = "그룹원 " + temp + "님에게 고열이 감지되었습니다."
            }

            override fun onFailure(call: Call<UserInfo>, t: Throwable) {
            }

        })

        return view
    }

}