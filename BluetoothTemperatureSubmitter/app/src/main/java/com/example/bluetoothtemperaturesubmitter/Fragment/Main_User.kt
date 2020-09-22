package com.example.bluetoothtemperaturesubmitter.Fragment

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.bluetoothtemperaturesubmitter.API.RetrofitHelper
import com.example.bluetoothtemperaturesubmitter.DTO.UserInfo
import com.example.bluetoothtemperaturesubmitter.R
import com.example.bluetoothtemperaturesubmitter.User.Measured_body_tempreture
import kotlinx.android.synthetic.main.fragment_main__user.*
import kotlinx.android.synthetic.main.fragment_main__user.view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class Main_User() : Fragment() {

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        val token : String? = arguments!!.getString("token")
        val pk = arguments?.getInt("pk")

        val root =  inflater.inflate(R.layout.fragment_main__user, container, false)

        if (token != null && pk != 0) {
            RetrofitHelper().getUserAPI().getUser(token, pk.toString()).enqueue(object : Callback<UserInfo> {
                override fun onResponse(call: Call<UserInfo>, response: Response<UserInfo>) {
                    Log.d("TAG", token)
                    Log.d("PK", pk.toString())
                    if (response.isSuccessful) {
                        Log.d("TAG", "ERROR")
                        if (response.body() != null) {
                            Log.d("TAG", "ERROR")
                            my_name.text = response.body()!!.username
                            my_email.text = response.body()!!.email

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
                activity!!.startActivity(intent)
            } else {
                Log.d("ERROR", "ERROR")
            }
        }

        return root
    }
}