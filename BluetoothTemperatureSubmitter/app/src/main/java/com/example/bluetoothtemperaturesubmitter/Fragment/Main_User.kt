package com.example.bluetoothtemperaturesubmitter.Fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.bluetoothtemperaturesubmitter.API.RetrofitHelper
import com.example.bluetoothtemperaturesubmitter.DTO.UserInfo
import com.example.bluetoothtemperaturesubmitter.R
import kotlinx.android.synthetic.main.fragment_main__user.*
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
            RetrofitHelper(context).getUserAPI().getUser(token, pk.toString()).enqueue(object : Callback<UserInfo> {
                override fun onResponse(call: Call<UserInfo>, response: Response<UserInfo>) {
                    Log.d("TAG", token)
                    Log.d("PK", pk.toString())
                    if(response.isSuccessful){
                        Log.d("TAG", "ERROR")
                        if(response.body() != null){
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
        return root
    }
}