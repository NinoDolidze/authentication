package com.example.authentication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

class RegisterActivity : AppCompatActivity() {

    private lateinit var editTextEmail: EditText
    private lateinit var editTextPassword: EditText
    private lateinit var editTextRepeatPassword: EditText
    private lateinit var buttonRegister: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        init()

        registerListeners()
    }

    private fun init() {
        editTextEmail = findViewById(R.id.editTextEmail)
        editTextPassword = findViewById(R.id.editTextPassword)
        editTextRepeatPassword = findViewById(R.id.editTextRepeatPassword)
        buttonRegister = findViewById(R.id.buttonRegister)

    }

//    private fun validatePassword(): Boolean {
//        val password1 = editTextPassword.text.toString()
//        val password2 = editTextRepeatPassword.text.toString()
//
//        if (password1 == password2) {
//            return true
//        } else {
//            return false
//    }

    private fun registerListeners() {
        buttonRegister.setOnClickListener() {
            val email = editTextEmail.text.toString()
            val password = editTextPassword.text.toString()
            val repeatPassword = editTextRepeatPassword.toString()

            if (email.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "empty!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (email.length < 11) {
                Toast.makeText(this, "Email is too short!", Toast.LENGTH_SHORT).show()
            }

            if (password.length <6 || repeatPassword.length < 6 ) {
                Toast.makeText(this, "password is short!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (password != repeatPassword) {
                Toast.makeText(this, "passwords do not match", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        startActivity(Intent(this, LoginActivity::class.java))
                        finish()
                    } else {
                        Toast.makeText(this, "Error!", Toast.LENGTH_SHORT).show()
                    }
                }
        }
    }

}