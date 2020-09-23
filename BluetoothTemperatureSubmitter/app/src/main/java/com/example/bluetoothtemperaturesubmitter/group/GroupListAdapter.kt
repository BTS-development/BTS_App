package com.example.bluetoothtemperaturesubmitter.group

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.example.bluetoothtemperaturesubmitter.DTO.Groups
import com.example.bluetoothtemperaturesubmitter.R

class GroupListAdapter(context: Context, dataList : List<Groups>) : BaseAdapter(){
    private val mContext : Context = context
    private val groupData = dataList



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


        return view
    }

}