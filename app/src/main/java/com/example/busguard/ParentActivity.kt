package com.example.busGuard

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.busguard.HomeActivity
import com.example.busguard.ParentSignupActivity

class ParentActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.parent_login)

        val loginButton = findViewById<Button>(R.id.loginButton)
        val signupButton = findViewById<Button>(R.id.signupButton)

        loginButton.setOnClickListener {
            startActivity(Intent(ParentActivity@this,HomeActivity::class.java))
            finish()
        }
        signupButton.setOnClickListener {
            startActivity(Intent(ParentActivity@this,ParentSignupActivity::class.java))
            finish()
        }
    }
}