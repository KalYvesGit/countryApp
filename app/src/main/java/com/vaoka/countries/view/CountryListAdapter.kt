package com.vaoka.countries.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.vaoka.countries.R
import com.vaoka.countries.model.Country
import com.vaoka.countries.util.getProgressDrawable
import com.vaoka.countries.util.loadImage
import kotlinx.android.synthetic.main.item_country.view.*
import kotlin.collections.ArrayList

class CountryListAdapter(var countries: ArrayList<Country>):
    RecyclerView.Adapter<CountryListAdapter.CountryViewHolder>() {


    fun updateCountries(newCountries: List<Country>){
        countries.clear()
        countries.addAll(newCountries)
        notifyDataSetChanged()
    }

    //creates the CountryViewHolder class
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =  CountryViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.item_country, parent, false)
    )

    //this method just return the number of items in the list
    override fun getItemCount() = countries.size


    //this calls the bind function
    override fun onBindViewHolder(holder: CountryViewHolder, position: Int) {
        holder.bind(countries[position])
    }

    class CountryViewHolder(view: View): RecyclerView.ViewHolder(view) {

        //attach country to the layout
        //view is the layout and name is the the id of the element(TextView) inside
        private val countryName = view.name
        private val countryCapital = view.capital
        private val countryFlag = view.imageView
        private val progressDrawable = getProgressDrawable(view.context)

        fun bind(country: Country){
            countryName.text = country.countryName
            countryCapital.text = country.countryCapital
            countryFlag.loadImage(country.flag, progressDrawable)

        }
    }


}