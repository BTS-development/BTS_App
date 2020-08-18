package com.example.bluetoothtemperaturesubmitter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView

class GroupClass(val context: Context, val group_list: ArrayList<Group>) : BaseAdapter(){
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view: View = LayoutInflater.from(context).inflate(R.layout.group_management,null)

        val group_image = view.findViewById<ImageView>(R.id.group_image)
        val group_name = view.findViewById<TextView>(R.id.group_name)
        val group_people = view.findViewById<TextView>(R.id.group_people)


        val group = group_list[position]
        val resourceId = context.resources.getIdentifier(group.image, "drawable", context.packageName)
        group_image.setImageResource(resourceId)
        group_name.text = group.name
        group_people.text = group.people

        return view
    }

    override fun getItem(position: Int): Any {
        return group_list[position]
    }

    override fun getItemId(position: Int): Long {
        return 0
    }

    override fun getCount(): Int {
        return group_list.size
    }

}