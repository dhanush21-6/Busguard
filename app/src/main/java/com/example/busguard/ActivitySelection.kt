package com.example.busguard

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.busGuard.ParentActivity
import com.example.busGuard.R

class ActivitySelection : AppCompatActivity() {

    private val TAG = "ActivitySelection"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_selection)
        val parentButton = findViewById<Button>(R.id.button1)
        parentButton.setOnClickListener {
            parentlogin()
        }
    }

    fun parentlogin(){
        Log.d(TAG, "parentlogin: ")
        val intent= Intent(this, ParentActivity::class.java)
        startActivity(intent)
        finish()
    }
}
