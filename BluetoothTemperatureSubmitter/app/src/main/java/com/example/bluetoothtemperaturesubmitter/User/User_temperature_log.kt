package com.example.bluetoothtemperaturesubmitter.User

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView

import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.example.bluetoothtemperaturesubmitter.API.RetrofitHelper
import com.example.bluetoothtemperaturesubmitter.DTO.Temperature
import com.example.bluetoothtemperaturesubmitter.R
import kotlinx.android.synthetic.main.activity_user_temperature_log.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class User_temperature_log : AppCompatActivity() {

    var tempData = ArrayList<Temperature>()
    private val tempAPI = RetrofitHelper().getTemperatureAPI()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_temperature_log)
        tempAPI.getMyTemp(intent.getStringExtra("token")).enqueue(object : Callback<List<Temperature>>{
            override fun onFailure(call: Call<List<Temperature>>, t: Throwable) {
                Toast.makeText(this@User_temperature_log, "오류 발생 : $t", Toast.LENGTH_LONG).show()
            }

            @SuppressLint("SetTextI18n")
            override fun onResponse(
                call: Call<List<Temperature>>,
                response: Response<List<Temperature>>
            ) {
                Log.d("ERROR", response.code().toString())
                if(response.isSuccessful){
                    Log.d("ERROR", response.code().toString())
                    if(response.body() != null){
                        var i = response.body()!!.size - 1
                        if(i > -1) {
                            while (i >= 0) {
                                tempData.add(response.body()!![i--])
                            }
                            my_temperature.text = tempData[0].value.toString() + "℃"
                            if (tempData[0].value >= 37.5) {
                                my_temperature_state.setBackgroundDrawable(
                                    ContextCompat.getDrawable(
                                        this@User_temperature_log,
                                        R.drawable.high_temp
                                    )
                                )
                                state_text.text = "고열"
                            } else if (tempData[0].value <= 35.5) {
                                my_temperature_state.setBackgroundDrawable(
                                    ContextCompat.getDrawable(
                                        this@User_temperature_log,
                                        R.drawable.ractangle
                                    )
                                )
                                state_text.text = "저체온"
                            }
                        }
                        else if(i == -1){
                            my_temperature.text = "0℃"
                            my_temperature_state.setBackgroundDrawable(ContextCompat.getDrawable(this@User_temperature_log, R.drawable.zero_degree))
                            state_text.text = "미측정"
                        }
                    } else {
                        Log.d("ERROR", response.code().toString())
                    }
                }
            }

        })
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
                if (parent != null) {
                    when (position) {
                        0 -> {
                            selectAll()
                        }
                        1 -> {
                            selectHighTemp()
                        }
                        2 -> {
                            selectTen()
                        }
                    }
                }
            }

        }

    }
    private fun selectAll(){
        temperature_item.adapter = UserAdapter(this, tempData)
    }
    private fun selectTen(){
        var dataList = ArrayList<Temperature>()
        var i = 0
        while(i < 10){
            if(tempData.size < 10){
                dataList = tempData
                break
            }
            dataList.add(tempData[i])
            i++
        }
        temperature_item.adapter = UserAdapter(this, dataList)
    }
    private fun selectHighTemp(){
        val dataList = ArrayList<Temperature>()
        for(t in tempData){
            if(t.value >= 37.5 || t.value <= 35.5){
                dataList.add(t)
            }
        }
        temperature_item.adapter = UserAdapter(this, dataList)
    }
}