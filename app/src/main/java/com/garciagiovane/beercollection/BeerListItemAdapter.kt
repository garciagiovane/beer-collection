package com.garciagiovane.beercollection

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.garciagiovane.beercollection.database.entities.BeerEntity

class BeerListItemAdapter(
    context: Context,
    private val beers: List<BeerEntity>
) : BaseAdapter() {

    private val inflater: LayoutInflater = LayoutInflater.from(context)

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val beer = beers[position]

        if (convertView == null) {
            val view = inflater.inflate(R.layout.beerlistitem, parent, false)
            setDataToView(view, beer, position)
            return view
        }
        setDataToView(convertView, beer, position)
        return convertView
    }

    private fun setDataToView(convertView: View, beer: BeerEntity, position: Int) {
        convertView.findViewById<TextView>(R.id.beersListItemTvNameValue).text = beer.name
        convertView.findViewById<TextView>(R.id.beersListItemTvPurchaseDateValue).text =
            extractValueOrDefault(beer.purchaseDate, convertView)
        convertView.findViewById<TextView>(R.id.beersListItemTvPurchaseCommentValue).text =
            extractValueOrDefault(beer.comments, convertView)

        val grey = convertView.resources.getColor(R.color.grey_700, null)
        val white = convertView.resources.getColor(R.color.white, null)

        convertView.setBackgroundColor(if (position % 2 == 0) grey else white)
    }

    private fun extractValueOrDefault(value: String?, convertView: View): String {
        return if (value.isNullOrEmpty() || value == "null")
            convertView.resources.getText(R.string.lba_itemNotInformed).toString()
        else value
    }

    override fun getCount(): Int {
        return beers.size
    }

    override fun getItem(position: Int): Any {
        return beers[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }
}