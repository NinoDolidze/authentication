package com.example.authentication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

class LoginActivity : AppCompatActivity() {

    private lateinit var editTextEmail: EditText
    private lateinit var editTextPassword: EditText
    private lateinit var editTextRepeatPassword: EditText
    private lateinit var buttonLogin: Button
    private lateinit var buttonRegister: Button
    private lateinit var buttonResetPassword: Button



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        if(FirebaseAuth.getInstance().currentUser != null) {
            gotoProfile()
        }

        init()

        registerListeners()
    }

    private fun init() {
        editTextEmail = findViewById(R.id.editTextEmail)
        editTextPassword = findViewById(R.id.editTextPassword)
        buttonLogin = findViewById(R.id.buttonLogin)
        buttonRegister = findViewById(R.id.buttonRegister)
        buttonResetPassword = findViewById(R.id.buttonResetPassword)
        editTextRepeatPassword = findViewById(R.id.editTextRepeatPassword)


    }

    private fun registerListeners() {

        buttonRegister.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
        }
        buttonResetPassword.setOnClickListener {
            startActivity(Intent(this, PasswordResetActivity::class.java))
        }
        buttonLogin.setOnClickListener {

            val email = editTextEmail.text.toString()
            val password = editTextPassword.text.toString()
            val repeatPassword = editTextRepeatPassword.text.toString()

                if (!email.isEmpty() && !password.isEmpty() && !repeatPassword.isEmpty()) {
                } else {
                    Toast.makeText(this, "Empty!", Toast.LENGTH_SHORT).show()
                    return@setOnClickListener
                }

                if (password.length < 9 || repeatPassword.length < 9 ) {
                    Toast.makeText(this, "Password Is Too Short!", Toast.LENGTH_SHORT).show()
                    return@setOnClickListener
                }

                if (password != repeatPassword) {
                    Toast.makeText(this, "Passwords Do Not Match!", Toast.LENGTH_SHORT).show()
                    return@setOnClickListener
                } else if (email.contains("@gmail.com") && email.length > 11 && password.length > 9 && repeatPassword.length > 9 )


            FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        gotoProfile()
                    } else {
                        Toast.makeText(this, "Error!", Toast.LENGTH_SHORT).show()
                    }
                }
        }

    }
    private fun gotoProfile() {
        startActivity(Intent(this, ProfileActivity::class.java))
        finish()
    }

}