package com.example.bluetoothtemperaturesubmitter.User

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView

import android.widget.ArrayAdapter
import com.example.bluetoothtemperaturesubmitter.R
import kotlinx.android.synthetic.main.activity_user_temperature_log.*

class User_temperature_log : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_temperature_log)
        val menu = ArrayList<String>()
        menu.add("전체")
        menu.add("이상 수치만")
        menu.add("최근 내역 10개")
        val adapter = ArrayAdapter<String>(this@User_temperature_log, android.R.layout.simple_spinner_dropdown_item,menu)

        spinner.adapter = adapter
        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {
                spinner.setSelection(0)
            }

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {

            }

        }

    }
    private fun selectAll(){

    }
    private fun selectTem(){

    }
    private fun selectHighTemp(){

    }
}