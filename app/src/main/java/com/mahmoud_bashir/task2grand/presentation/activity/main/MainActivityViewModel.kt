package com.mahmoud_bashir.task2grand.presentation.activity.main

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.mahmoud_bashir.task2grand.data.repository.user_info_repo.GetUserInfoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor(app:Application,val repo:GetUserInfoRepository):AndroidViewModel(app){

     fun getUserInfo() = viewModelScope.launch {
        repo.apiServer.getUserInfo(10)
            .also {
                response ->
                if (response.isSuccessful){
                    val body = response.body()
                    Log.d("bodyResponse : ","msg  : "+body?.email)
                }
            }
    }

}