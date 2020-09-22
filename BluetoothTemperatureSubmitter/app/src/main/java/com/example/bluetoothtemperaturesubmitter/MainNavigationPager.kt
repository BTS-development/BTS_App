package com.example.bluetoothtemperaturesubmitter

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.example.bluetoothtemperaturesubmitter.Fragment.GroupFragment
import com.example.bluetoothtemperaturesubmitter.Fragment.Main_User
import com.example.bluetoothtemperaturesubmitter.Fragment.Setting
import kotlinx.android.synthetic.main.activity_main__pager.*

class MainNavigationPager : AppCompatActivity() {
    private val fragmentManager: FragmentManager = supportFragmentManager
    private val fragmentButton : Main_User = Main_User()
    private val setting : Setting = Setting()
    private val groupFragment : GroupFragment = GroupFragment()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main__pager)

        val intent = intent

        val bundle = Bundle()
        bundle.putString("token", intent.getStringExtra("token"))
        bundle.putInt("pk",intent.getIntExtra("pk",0))

        groupFragment.arguments = bundle
        fragmentButton.arguments = bundle

        val transaction: FragmentTransaction = fragmentManager.beginTransaction()
        transaction.replace(R.id.frame, fragmentButton).commitAllowingStateLoss()
        initView()

    }
    private fun initView() {

        navigation.setOnNavigationItemSelectedListener {item ->
            val transaction: FragmentTransaction = fragmentManager.beginTransaction()
            when(item.itemId){

                R.id.page_1 -> {
                    transaction.replace(R.id.frame,fragmentButton).commitAllowingStateLoss()
                    true
                }
                R.id.page_2 -> {
                    transaction.replace(R.id.frame, groupFragment).commitAllowingStateLoss()
                    true
                }
                R.id.page_3 -> {
                    transaction.replace(R.id.frame, setting).commitAllowingStateLoss()
                    true
                }
                else -> false
            }
        }
    }
}