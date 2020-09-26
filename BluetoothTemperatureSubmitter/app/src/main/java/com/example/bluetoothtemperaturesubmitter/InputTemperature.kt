package com.example.bluetoothtemperaturesubmitter

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.bluetoothtemperaturesubmitter.API.RetrofitHelper
import com.example.bluetoothtemperaturesubmitter.API.TemperatureAPI
import com.example.bluetoothtemperaturesubmitter.DTO.Temperature
import kotlinx.android.synthetic.main.activity_input_temperature.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class InputTemperature : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_input_temperature)

        val service = RetrofitHelper().getTemperatureAPI()

        input_button.setOnClickListener {
            service.postTemp(intent.getStringExtra("token"),inputTemp.text.toString().toDouble()).enqueue(object : Callback<Temperature>{
                override fun onFailure(call: Call<Temperature>, t: Throwable) {

                }

                override fun onResponse(call: Call<Temperature>, response: Response<Temperature>) {
                    if (response.isSuccessful){
                        Toast.makeText(this@InputTemperature, "온도 : " + inputTemp.text.toString(), Toast.LENGTH_LONG).show()
                        finish()
                    }
                }

            })
        }
    }
}