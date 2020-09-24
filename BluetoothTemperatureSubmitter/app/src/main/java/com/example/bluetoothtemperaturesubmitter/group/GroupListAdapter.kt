package com.example.bluetoothtemperaturesubmitter.group

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.example.bluetoothtemperaturesubmitter.API.RetrofitHelper
import com.example.bluetoothtemperaturesubmitter.DTO.Groups
import com.example.bluetoothtemperaturesubmitter.DTO.User
import com.example.bluetoothtemperaturesubmitter.R
import kotlinx.android.synthetic.main.group_management.view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class GroupListAdapter(context: Context, dataList : List<Groups>, token : String) : BaseAdapter(){
    private val mContext : Context = context
    private val groupData = dataList
    private val mToken = token



    override fun getCount(): Int {
        return groupData.size
    }

    override fun getItem(position: Int): Any {
        return groupData[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    @SuppressLint("ViewHolder")
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val layoutInflater = LayoutInflater.from(mContext)
        val view = layoutInflater.inflate(R.layout.group_management, parent, false)
        val groupNameList = view.findViewById<TextView>(R.id.group_name)
        groupNameList.text = groupData[position].name

        RetrofitHelper().getGroupAPI().getGroupMember(mToken, groupData[position].id.toString()).enqueue(object : Callback<List<User>>{
            override fun onFailure(call: Call<List<User>>, t: Throwable) {

            }

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
                view.group_people.text = text
            }

        })

        return view
    }

}