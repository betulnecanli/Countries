package com.betulnecanli.countries.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.betulnecanli.countries.Adapter.CountryAdapter
import com.betulnecanli.countries.R
import com.betulnecanli.countries.ViewModel.DashboardViewModel
import com.betulnecanli.countries.databinding.FragmentDashboardBinding
import kotlinx.android.synthetic.main.fragment_dashboard.*


class DashboardFragment : Fragment() {


      lateinit var binding : FragmentDashboardBinding
      private lateinit var viewModel : DashboardViewModel
      private val countryAdapter = CountryAdapter(arrayListOf())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDashboardBinding.inflate(layoutInflater,container,false)






        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this).get(DashboardViewModel::class.java)
        viewModel.refreshData()


        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = countryAdapter

        swipe.setOnRefreshListener {
            recyclerView.visibility = View.GONE
            errorText.visibility = View.GONE
            refreshBar.visibility = View.VISIBLE
            viewModel.refreshData()
            swipe.isRefreshing = false
        }

        observeLiveData()
    }



    fun observeLiveData(){
        viewModel.countries.observe(viewLifecycleOwner, Observer {countries->
            countries?.let{
                recyclerView.visibility = View.VISIBLE
                countryAdapter.updateCountryList(countries)
            }


        })


        viewModel.countryError.observe(viewLifecycleOwner, Observer {error ->

            error?.let {
                if(it){
                    errorText.visibility = View.VISIBLE
                }
                else{
                    errorText.visibility = View.GONE
                }
            }

        })


        viewModel.countryLoading.observe(viewLifecycleOwner, Observer { loading ->
            loading?.let {
                if(it){
                    refreshBar.visibility = View.VISIBLE
                    errorText.visibility = View.GONE
                    recyclerView.visibility = View.GONE
                }
                else{
                    refreshBar.visibility = View.GONE
                }
            }

        })
    }

}