package com.example.busguard

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.busGuard.R
import com.google.android.material.bottomnavigation.BottomNavigationView

@Suppress("DEPRECATION")
class SettingsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)

        val bottomNavigationView: BottomNavigationView = findViewById(R.id.bottomNavigation)
        bottomNavigationView.selectedItemId = R.id.bottom_settings

        bottomNavigationView.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.bottom_home -> {
                    startActivity(Intent(applicationContext, HomeActivity::class.java))
                    overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
                    finish()
                    return@setOnItemSelectedListener true
                }
                R.id.bottom_search -> {
                    startActivity(Intent(applicationContext, LiveActivity::class.java))
                    overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
                    finish()
                    return@setOnItemSelectedListener true
                }
                R.id.bottom_settings -> return@setOnItemSelectedListener true
                R.id.bottom_profile -> {
                    startActivity(Intent(applicationContext, ProfileActivity::class.java))
                    overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
                    finish()
                    return@setOnItemSelectedListener true
                }
            }
            false
        }
    }
}
