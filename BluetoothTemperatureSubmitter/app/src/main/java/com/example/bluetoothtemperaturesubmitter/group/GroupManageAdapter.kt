package com.example.bluetoothtemperaturesubmitter.group

import android.content.Context
import android.util.Log
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

class GroupManageAdapter (context: Context, manageList: ArrayList<User>, token : String) : BaseAdapter(){
    private val mContext : Context = context
    private val manageData = manageList
    private val mToken = token

    override fun getCount(): Int {
        return manageData.size
    }

    override fun getItem(position: Int): Any {
        return manageData[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val layoutInflater = LayoutInflater.from(mContext)
        val view = layoutInflater.inflate(R.layout.group_member_ui, parent, false)
        val manageText = view.findViewById<TextView>(R.id.Grout_member_name)

        Log.d("ERROR", manageData[position].username)

        manageText.text = manageData[position].username

        return view
    }

}