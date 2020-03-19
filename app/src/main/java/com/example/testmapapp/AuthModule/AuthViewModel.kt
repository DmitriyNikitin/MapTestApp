package com.example.testmapapp.AuthModule

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.annotation.NonNull
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import com.example.testmapapp.R


class AuthViewModel(val navController: NavController) : ViewModel(){

    private val liveData = MutableLiveData<Boolean>()

    fun showMapFragmnet(){
        navController.navigate(R.id.action_authFragment_to_mapFragment)
    }


    fun checkVerify(model: AuthModel){
       if(model.login == "test@starline.ru" && model.password == "test"){
           liveData.postValue(true)
           showMapFragmnet()
       }else{
           liveData.postValue(false)
       }
    }

    fun getLiveData():MutableLiveData<Boolean> {
        return liveData
    }
}

class AuthViewModelFactory(private val navController: NavController) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return AuthViewModel(navController) as T

    }
}