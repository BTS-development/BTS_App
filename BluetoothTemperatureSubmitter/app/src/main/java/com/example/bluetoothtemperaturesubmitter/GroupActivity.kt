package com.example.bluetoothtemperaturesubmitter

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_group.*

class GroupActivity : AppCompatActivity() {
    var group_list = arrayListOf<group>(
        group("group28","STAC 개발팀","정한빈,한빈,정빈,외 2명"),
        group("group28","현대 개발팀","정현빈,한빈,정빈,외 5명"),
        group("group28","삼성 개발팀","민반빈,현빈,반빈,외 6명"),
        group("group28","학교 개발팀","정문빈,한빈,정빈,외 4명")
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_group)

        val groupAdapter= Group_class(this,group_list)
        group_listview.adapter = groupAdapter
    }
}