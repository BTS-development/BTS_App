package com.example.bluetoothtemperaturesubmitter.User

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import androidx.core.content.ContextCompat
import com.example.bluetoothtemperaturesubmitter.DTO.Temperature
import com.example.bluetoothtemperaturesubmitter.R
import kotlinx.android.synthetic.main.user_temperature_item.view.*

class UserAdapter(val context: Context, private val arrayList: ArrayList<Temperature>) : BaseAdapter() {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view = convertView ?: LayoutInflater.from(context).inflate(R.layout.user_temperature_item, parent,false)

        if (arrayList[position].value >= 37.5){
            view.My_temperature_state_listview.setBackgroundDrawable (ContextCompat.getDrawable(context, R.drawable.high_temp))
        } else if(arrayList[position].value <= 35.5){
            view.My_temperature_state_listview.setBackgroundDrawable (ContextCompat.getDrawable(context, R.drawable.ractangle))
        }

        val token = arrayList[position].created_at.split('-')
        val time = token[1] + "." + token[2]
        view.user_date.text = time


        view.my_temperature_listview.text = arrayList[position].value.toString()

        return view
    }

    override fun getItem(position: Int): Any? {
        return null
    }

    override fun getItemId(position: Int): Long {
        return 0
    }

    override fun getCount(): Int {
        return arrayList.size
    }

}