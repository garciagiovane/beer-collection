package com.garciagiovane.beercollection.components

import android.app.DatePickerDialog
import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.util.Patterns
import android.view.View
import android.widget.DatePicker
import android.widget.TextView
import androidx.fragment.app.DialogFragment
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeFormatterBuilder
import java.util.*

class DatePickerFragment(val ctxt: Context, val originView: TextView) : DialogFragment(), DatePickerDialog.OnDateSetListener {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val c = LocalDate.now()
        val year = c.year
        val month = c.monthValue
        val day = c.dayOfMonth

        return DatePickerDialog(ctxt, this, year, month, day)
    }

    override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
        val date = LocalDate.of(year, month, dayOfMonth)
        val languageTag = ctxt.resources.configuration.locales.get(0).toLanguageTag()
        val formatter =
            DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.forLanguageTag(languageTag))

        originView.text = date.format(formatter).toString()
    }
}