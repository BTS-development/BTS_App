package com.example.bluetoothtemperaturesubmitter.User

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.bluetoothtemperaturesubmitter.Fragment.Main_User
import com.example.bluetoothtemperaturesubmitter.R

class MainPageActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_page)

        supportFragmentManager.beginTransaction()
            .replace(R.id.main_frame,Main_User())
            .commit()

    }

}