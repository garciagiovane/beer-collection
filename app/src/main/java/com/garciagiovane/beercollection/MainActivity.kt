package com.garciagiovane.beercollection

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    private var successMessage: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        successMessage = intent.extras?.get("SUCCESS")?.toString()

        findViewById<Button>(R.id.ma_btnAddBeer).setOnClickListener {
            val intent = Intent(this, AddBeerActivity::class.java)
            startActivity(intent)
        }

        findViewById<Button>(R.id.ma_btnListBeers).setOnClickListener {
            val intent = Intent(this, ListBeersActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onStart() {
        super.onStart()

        val tvInfoMessage = findViewById<TextView>(R.id.ma_tvInfoMessage)

        if (successMessage != null) {
            tvInfoMessage.text = successMessage
        }
    }
}