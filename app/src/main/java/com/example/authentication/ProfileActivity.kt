package com.example.authentication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.google.firebase.auth.FirebaseAuth

class ProfileActivity : AppCompatActivity() {

    private lateinit var buttonLogout: Button
    private lateinit var buttonChangePassword: Button
    private lateinit var textView: TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        init()

        registerListeners()

        textView.text = FirebaseAuth.getInstance().currentUser?.uid
    }

    private fun init() {
        buttonLogout = findViewById(R.id.buttonLogout)
        buttonChangePassword = findViewById(R.id.buttonChangePassword)
        textView = findViewById(R.id.textView)
    }

    private fun registerListeners() {
        buttonChangePassword.setOnClickListener {
            startActivity(Intent(this, ChangePasswordActivity::class.java))
        }
        buttonLogout.setOnClickListener {
            FirebaseAuth.getInstance().signOut()
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }
    }
}