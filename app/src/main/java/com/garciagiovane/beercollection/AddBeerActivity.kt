package com.garciagiovane.beercollection

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.garciagiovane.beercollection.components.DatePickerFragment
import com.garciagiovane.beercollection.database.DatabaseSingleton
import com.garciagiovane.beercollection.database.entities.BeerEntity
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeParseException
import java.util.*

class AddBeerActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_beer)
        title = resources.getText(R.string.adda_title)

        val beerDao = DatabaseSingleton.database(this).beerDao()
        val etPurchaseDate = findViewById<EditText>(R.id.adda_tvPurchaseDate)

        etPurchaseDate.setOnClickListener {
            showDatePickerDialog(it, etPurchaseDate)
        }

        findViewById<Button>(R.id.adda_btnAdd).setOnClickListener {
            val etName = findViewById<EditText>(R.id.adda_txtName)
            val name = etName.text
            if (name.isNullOrEmpty()) {
                etName.error = "Erro no input"
            } else {
                val comments = findViewById<EditText>(R.id.adda_txtComments).text
                val id = UUID.randomUUID().toString()
                val purchaseDate = etPurchaseDate.text.toString()
                val parsedDate = extractPurchaseDate(purchaseDate)

                val beerEntity =
                    BeerEntity(id, name.toString(), parsedDate.toString(), comments.toString())

                beerDao.save(beerEntity)

                val intent = Intent(this, MainActivity::class.java)
                val bundle = Bundle()
                bundle.putString("SUCCESS", getString(R.string.message_beer_saved))
                intent.putExtras(bundle)
                startActivity(intent)
            }
        }
    }

    private fun extractPurchaseDate(purchaseDate: String?): LocalDate? {
        if (purchaseDate?.isEmpty() == true) return null
        return try {
            LocalDate.parse(purchaseDate)
        } catch (e: DateTimeParseException) {
            val pattern = DateTimeFormatter.ofPattern("dd/MM/yyyy")
            LocalDate.parse(purchaseDate, pattern)
        }
    }

    private fun showDatePickerDialog(v: View, originView: TextView) {
        DatePickerFragment(v.context, originView).show(supportFragmentManager, "purchaseDate")
    }
}