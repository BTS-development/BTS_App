package com.example.bluetoothtemperaturesubmitter

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toolbar
import com.example.bluetoothtemperaturesubmitter.Fragment.Main_User
import kotlinx.android.synthetic.main.activity_main_page.*

class MainPageActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_page)

        supportFragmentManager.beginTransaction()
            .replace(R.id.main_frame,Main_User())
            .commit()

    }

}