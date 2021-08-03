package com.android.loginSignup

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.google.android.gms.tasks.OnCompleteListener
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    lateinit var password: String
    lateinit var email: String
    lateinit var sharedPreferences: SharedPreferences


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        sharedPreferences = getSharedPreferences("User Details", Context.MODE_PRIVATE)
        getUserDetails()


        btn_login.setOnClickListener {


            if (!sharedPreferences.contains("EMAIL")) {
                email = text_email.text.toString()
                password = text_password.text.toString()

                if (chck_remember.isChecked) { //remember user details
                    remember_user(email, password)
                }
                getUserDetails(email, password)
                } else {
                getUserDetails()
                }
        }

        btn_signup.setOnClickListener {
            email = text_email.text.toString()
            password = text_password.text.toString()
            signUpUser(email, password)
        }
    }

    private fun getUserDetails() {
        sharedPreferences = getSharedPreferences("User Details", Context.MODE_PRIVATE)
        val eml = sharedPreferences.getString("EMAIL", " ")
        val pwd = sharedPreferences.getString("PASSWORD", " ")
        getUserDetails(eml, pwd)
    }

    private fun rememberUserDetails(email: String, password: String) {
        sharedPreferences = getSharedPreferences("User Details", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putString("EMAIL", email)
        editor.putString("PASSWORD", password)
        editor.apply()
    }

    

    //List View after login
    var myrv: RecyclerView = findViewById<RecyclerView>(R.id.recyclerView)
    myrv.layoutManager = LinearLayoutManager(this,RecyclerView.VERTICAL,false)
    var myDataList = ArrayList<UserData>()
    myDataList.add(UserData("John"))
    myDataList.add(UserData("Helen"))
    myDataList.add(UserData("Rocky"))
    myDataList.add(UserData("Andrew"))
    myrv.adapter = MyAdapter(myDataList)

}
