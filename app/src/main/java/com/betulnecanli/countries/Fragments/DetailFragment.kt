package com.betulnecanli.countries.Fragments

import android.os.Bundle
import android.os.Debug
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.betulnecanli.countries.R
import com.betulnecanli.countries.Util.downloadFromUrl
import com.betulnecanli.countries.Util.placeholderProgressBar
import com.betulnecanli.countries.ViewModel.DetailViewModel
import kotlinx.android.synthetic.main.fragment_detail.*
import kotlinx.android.synthetic.main.fragment_detail.cName
import kotlinx.android.synthetic.main.item_country.*


class DetailFragment : Fragment() {

        private lateinit var viewModel: DetailViewModel
        private var countryUuid = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.let {
            countryUuid = DetailFragmentArgs.fromBundle(it).countryId
        }

        viewModel = ViewModelProvider(this).get(DetailViewModel::class.java)
        viewModel.getDataFromRoom(countryUuid)
        println("jolie " + countryUuid )
        observeLiveData()
    }

    private fun observeLiveData(){

        viewModel.countryLiveData.observe(viewLifecycleOwner, Observer { country->
            country?.let{
                cName.text = country.countryName
                capName.text = country.countryCapital
                regionName.text = country.countryRegion
                langName.text = country.countryLanguage
                currencyName.text = country.countryCurrency
                detailImg.downloadFromUrl(country.imageURL, placeholderProgressBar(requireContext()))


            }

        })
    }


}