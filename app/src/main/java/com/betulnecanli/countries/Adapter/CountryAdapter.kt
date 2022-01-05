package com.betulnecanli.countries.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.betulnecanli.countries.Fragments.DashboardFragmentDirections
import com.betulnecanli.countries.Model.Country
import com.betulnecanli.countries.R
import com.betulnecanli.countries.Util.downloadFromUrl
import com.betulnecanli.countries.Util.placeholderProgressBar
import com.betulnecanli.countries.databinding.ItemCountryBinding
import kotlinx.android.synthetic.main.item_country.view.*

class CountryAdapter(val countryList: ArrayList<Country>): RecyclerView.Adapter<CountryAdapter.CountryViewHolder>() {




    class CountryViewHolder(var view : View): RecyclerView.ViewHolder(view) {

        val binding = ItemCountryBinding.bind(view)


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryViewHolder {
        val inflater  = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.item_country, parent, false)
        return CountryViewHolder(view)
    }

    override fun onBindViewHolder(holder: CountryViewHolder, position: Int) {
        holder.binding.cName.text = countryList[position].countryName
        holder.binding.dName.text = countryList[position].countryRegion

        holder.view.setOnClickListener {
            val action = DashboardFragmentDirections.actionDashboardFragmentToDetailFragment(countryList[position].uuid)

            Navigation.findNavController(it).navigate(action)
        }

        holder.view.imageName.downloadFromUrl(countryList[position].imageURL, placeholderProgressBar(holder.view.context))


    }

    override fun getItemCount(): Int {
        return countryList.size
    }


    fun updateCountryList(newCountryList : List<Country>){
        countryList.clear()
        countryList.addAll(newCountryList)
        notifyDataSetChanged()
    }

}