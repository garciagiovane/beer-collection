package com.garciagiovane.beercollection

import android.os.Bundle
import android.widget.ListView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.garciagiovane.beercollection.database.AppDatabase
import com.garciagiovane.beercollection.database.DatabaseSingleton

class ListBeersActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_beers)

        title = resources.getText(R.string.lba_title)

        val tvMessage: TextView = findViewById(R.id.lba_tvMessage)
        val database: AppDatabase = DatabaseSingleton.database(this)
        val beers = database.beerDao().findAll()

        if (!beers.isNullOrEmpty()) {
            val listView = findViewById<ListView>(R.id.lba_listBeers)
            listView.adapter = BeerListItemAdapter(this, beers)
        } else {
            tvMessage.text = resources.getText(R.string.lba_emptyBeersMessage)
        }
    }
}