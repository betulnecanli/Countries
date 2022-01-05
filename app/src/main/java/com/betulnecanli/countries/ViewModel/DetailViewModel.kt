package com.betulnecanli.countries.ViewModel

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.betulnecanli.countries.Model.Country
import com.betulnecanli.countries.Service.CountryDatabase
import kotlinx.coroutines.launch
import java.util.*

class DetailViewModel(application: Application) : BaseViewModel(application){


    val countryLiveData  = MutableLiveData<Country>()


    fun getDataFromRoom(uuid: Int){
        launch {
            val dao  = CountryDatabase(getApplication()).countryDao()
            val country = dao.getCountry(uuid)
            countryLiveData.value = country
        }
    }
}