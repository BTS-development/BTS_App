package com.example.bluetoothtemperaturesubmitter.Fragment

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import com.example.bluetoothtemperaturesubmitter.API.RetrofitHelper
import com.example.bluetoothtemperaturesubmitter.DTO.Temperature
import com.example.bluetoothtemperaturesubmitter.DTO.UserInfo
import com.example.bluetoothtemperaturesubmitter.R
import com.example.bluetoothtemperaturesubmitter.User.Measured_body_tempreture
import com.example.bluetoothtemperaturesubmitter.User.User_temperature_log
import kotlinx.android.synthetic.main.fragment_main__user.*
import kotlinx.android.synthetic.main.fragment_main__user.view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.time.Duration
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*
import kotlin.collections.ArrayList


class Main_User() : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val token : String? = arguments!!.getString("token")
        val pk = arguments?.getInt("pk")
        val todayTemp = ArrayList<Temperature>()
        val yesterdayTemp = ArrayList<Temperature>()
        val twoDaysAgoTemp = ArrayList<Temperature>()
        val root =  inflater.inflate(R.layout.fragment_main__user, container, false)
        var todayTemperature = 0.0
        var yesterdayTemperature = 0.0
        var twoDaysAgoTemperature = 0.0

        if (token != null && pk != 0) {
            RetrofitHelper().getUserAPI().getUser(token, pk.toString()).enqueue(object : Callback<UserInfo> {
                override fun onResponse(call: Call<UserInfo>, response: Response<UserInfo>) {
                    Log.d("TAG", token)
                    Log.d("PK", pk.toString())
                    if (response.isSuccessful) {
                        Log.d("TAG", "ERROR")
                        if (response.body() != null) {
                            Log.d("TAG", "ERROR")
                            my_name?.text = response.body()!!.username
                            my_email?.text = response.body()!!.email
                            RetrofitHelper().getTemperatureAPI().getMyTemp(token).enqueue(object : Callback<List<Temperature>>{
                                override fun onFailure(
                                    call: Call<List<Temperature>>,
                                    t: Throwable
                                ) {}

                                @SuppressLint("SetTextI18n")
                                @RequiresApi(Build.VERSION_CODES.O)
                                override fun onResponse(
                                    call: Call<List<Temperature>>,
                                    response: Response<List<Temperature>>
                                ) {
                                    if(response.isSuccessful){
                                        if(response.body() != null){
                                            for(t : Temperature in response.body()!!){
                                                val today = LocalDate.now().atStartOfDay()
                                                val tempDate = LocalDate.parse(t.created_at, DateTimeFormatter.ISO_DATE).atStartOfDay()
                                                when (Duration.between(tempDate, today).toDays().toInt()){
                                                    0 -> todayTemp.add(t)
                                                    1 -> yesterdayTemp.add(t)
                                                    2 -> twoDaysAgoTemp.add(t)
                                                }
                                            }
                                            if(todayTemp.size > 0){
                                                for(t : Temperature in todayTemp){
                                                    if(todayTemperature < t.value){
                                                        todayTemperature = t.value
                                                    }
                                                }
                                                today_result?.text = todayTemperature.toString()
                                            } else {
                                                today_result?.text = "오늘 측정 결과가\n 없습니다"
                                            }
                                            if(yesterdayTemp.size > 0){
                                                for(t : Temperature in yesterdayTemp){
                                                    if(yesterdayTemperature < t.value){
                                                        yesterdayTemperature = t.value
                                                    }
                                                }
                                                yesterday_result?.text = yesterdayTemperature.toString()
                                            } else {
                                                yesterday_result?.text = "어제 측정 결과가\n 없습니다"
                                            }
                                            if(twoDaysAgoTemp.size > 0){
                                                for(t : Temperature in twoDaysAgoTemp){
                                                    if(twoDaysAgoTemperature < t.value){
                                                        twoDaysAgoTemperature = t.value
                                                    }
                                                }
                                                days_before_result?.text = todayTemperature.toString()
                                            } else {
                                                days_before_result?.text = "이틀전 측정 결과가\n 없습니다"
                                            }
                                        }
                                    }
                                }
                            })
                        }
                    }

                }

                override fun onFailure(call: Call<UserInfo>, t: Throwable) {
                    Log.d("ERROR", t.toString())
                }

            })

        }

        root.go_temperatures.setOnClickListener {
            if(activity != null) {
                val intent = Intent(activity, Measured_body_tempreture::class.java)
                intent.putExtra("token",token)
                activity!!.startActivity(intent)
            } else {
                Log.d("ERROR", "ERROR")
            }
        }

        root.go_temperature_detail.setOnClickListener {
            if(activity != null) {
                val intent = Intent(activity, User_temperature_log::class.java)
                intent.putExtra("token",token)
                activity!!.startActivity(intent)
            } else {
                Log.d("ERROR", "ERROR")
            }
        }

        return root
    }
}