package com.example.bluetoothtemperaturesubmitter.Fragment

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.bluetoothtemperaturesubmitter.MainActivity
import com.example.bluetoothtemperaturesubmitter.R
import kotlinx.android.synthetic.main.fragment_setting.view.*


class Setting : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val root =  inflater.inflate(R.layout.fragment_setting, container, false)

        root.Logout.setOnClickListener {
            logOut()
            startActivity(Intent(activity, MainActivity::class.java))
            activity!!.finish()
        }


        return root
    }

    fun logOut(){
        val pref = activity!!.getSharedPreferences("user", Activity.MODE_PRIVATE)
        val editor = pref.edit()
        editor.clear()
        editor.apply()
    }

}