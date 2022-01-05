package com.betulnecanli.countries.Service

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.betulnecanli.countries.Model.Country


@Database(entities = arrayOf(Country::class ), version = 1)
abstract class CountryDatabase : RoomDatabase() {


    abstract  fun countryDao() : CountryDAO


    //Singlete


    companion object{
        //volatile farklı threadlerden çağrılabilmesi için
        @Volatile private var instance : CountryDatabase? = null

        private val lock = Any()
        operator fun invoke(context: Context) = instance ?: synchronized(lock){
            instance ?: makeDatabase(context).also {
                instance = it
            }
        }


        private fun makeDatabase(context: Context) = Room.databaseBuilder(
            context.applicationContext,CountryDatabase::class.java, "countrydatabase"
        ).build()

    }
}