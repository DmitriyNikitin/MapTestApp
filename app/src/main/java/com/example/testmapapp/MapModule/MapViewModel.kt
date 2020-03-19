package com.example.testmapapp.MapModule

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.testmapapp.App
import com.example.testmapapp.R
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.coroutines.coroutineContext

class MapViewModel : ViewModel() {

    private val liveData = MutableLiveData<MapModel>()
    private val coordinateList = App.application?.resources?.getStringArray(R.array.coordinates)
    fun getLiveData(): LiveData<MapModel>{
        return liveData
    }


    fun startChangeMarker(){


        viewModelScope.launch(Dispatchers.IO) {
            while(true){
                for(item in coordinateList!!){
                    delay(1000)
                    liveData.postValue(MapModel(item.split(",")[0].toFloat(), item.split(",")[1].toFloat()))
                }
            }
        }



        //startChangeMarker()

    }

}