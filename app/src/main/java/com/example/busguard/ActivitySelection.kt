package com.example.busguard

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat.startActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.example.busGuard.ParentActivity
import com.example.busGuard.R

class ActivitySelection : AppCompatActivity() {

    private val TAG = "ActivitySelection"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Delay for 3 seconds
//        Handler(Looper.getMainLooper()).postDelayed({
            installSplashScreen()
//        }, 3000)

        setContentView(R.layout.activity_selection)
        val parentButton = findViewById<Button>(R.id.button1)
        parentButton.setOnClickListener {
            parentlogin()
        }
        val driverButton = findViewById<Button>(R.id.button)
        driverButton.setOnClickListener {
            driverlogin()
        }
    }


    fun parentlogin() {
        val intent = Intent(this, ParentActivity::class.java)
        startActivity(intent)
        finish()
    }


    fun driverlogin() {
        val intent = Intent(this, DriverLoginActivity::class.java)
        startActivity(intent)
        finish()
    }


}
