package com.example.bluetoothtemperaturesubmitter

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import com.example.bluetoothtemperaturesubmitter.API.RetrofitHelper
import com.example.bluetoothtemperaturesubmitter.DTO.Signup
import com.google.android.material.textfield.TextInputEditText
import kotlinx.android.synthetic.main.activity_signup.*
import retrofit2.Call
import retrofit2.Response

class SignupActivity : AppCompatActivity() {
    lateinit var usernameView : TextInputEditText
    lateinit var userPassword1View : TextInputEditText
    lateinit var userPassword2View : TextInputEditText
    lateinit var signName : TextInputEditText
    lateinit var signUp : Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)

        initView(this@SignupActivity)

        btnAddUser.setOnClickListener {
            setUpListener(this)
        }
    }
    fun setUpListener(activity: Activity){
        val email = usernameView.text.toString()
        val username = signName.text.toString()
        val password1 = userPassword1View.text.toString()
        val password2 = userPassword2View.text.toString()
        if (password1 == password2){
            RetrofitHelper().getUserAPI().signUp(username, email, password1, password2).enqueue(object : retrofit2.Callback<Signup> {
                override fun onResponse(call: Call<Signup>, response: Response<Signup>) {
                    when (response.code()) {
                        201 -> {
                            Toast.makeText(this@SignupActivity , "회원가입 성공", Toast.LENGTH_SHORT).show()
                            val intent = Intent(this@SignupActivity,MainActivity::class.java)
                            startActivity(intent)
                        }
                        400 -> {
                            Toast.makeText(this@SignupActivity, "회원가입 실패: 아이디나 비번이 올바르지 않습니다.", Toast.LENGTH_SHORT).show()
                        }
                        500 -> {
                            Toast.makeText(this@SignupActivity, "회원가입 실패: 서버 오류", Toast.LENGTH_SHORT).show()
                        }
                        else -> {
                            Toast.makeText(this@SignupActivity, "회원가입 실패: 이유 불명" + response.code(), Toast.LENGTH_SHORT).show()
                        }
                    }
                }

                override fun onFailure(call: Call<Signup>, t: Throwable) {
                    Log.d("ERROR", t.toString())
                }

            })
        }
        else {
            Toast.makeText(this@SignupActivity,"비밀번호가 맞지않습니다.",Toast.LENGTH_SHORT).show()
        }
    }


    fun initView(activity: Activity){
        usernameView = activity.findViewById(R.id.signUpID)
        userPassword1View = activity.findViewById(R.id.signUpPassword)
        userPassword2View = activity.findViewById(R.id.checkPwd)
        signUp = activity.findViewById(R.id.btnAddUser)
        signName = activity.findViewById(R.id.signUpName)
    }
}