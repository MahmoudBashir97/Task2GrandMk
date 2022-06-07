package com.mahmoud_bashir.task2grand.presentation.fragment.profile

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.mahmoud_bashir.task2grand.data.repository.get_albums_repo.GetAlbumsRepository
import com.mahmoud_bashir.task2grand.data.repository.user_info_repo.GetUserInfoRepository
import com.mahmoud_bashir.task2grand.data.response.get_albums.GetAlbumsResponseItem
import com.mahmoud_bashir.task2grand.data.response.get_user_info.GetUserInfoResponse
import com.mahmoud_bashir.task2grand.utilities.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileFragmentViewModel @Inject constructor(app:Application,val repo:GetUserInfoRepository,
val albumsRepo:GetAlbumsRepository) : AndroidViewModel(app) {

    val userInfoResponse: MutableLiveData<Resource<GetUserInfoResponse>> = MutableLiveData()
    val userAlbumsResponse : MutableLiveData<Resource<List<GetAlbumsResponseItem>>> = MutableLiveData()

    fun getUserInfo(userId:Int) = viewModelScope.launch {
        repo.apiServer.getUserInfo(userId)
            .also {
                    response ->
                if (response.isSuccessful){
                    val body = response.body()
                    userInfoResponse.postValue(Resource.success(body))
                    Log.d("bodyResponse : ","msg  : "+body?.email)
                }else{
                    userInfoResponse.postValue(Resource.error(response.errorBody().toString(),null))
                }
            }
    }

    fun getUserAlbumsList(userId: Int)=viewModelScope.launch {
        albumsRepo.getAlbumsList(userId)
            .also {
                response ->
                if (response.isSuccessful){
                    val body = response.body()
                    userAlbumsResponse.postValue(Resource.success(body))
                }else{
                    userAlbumsResponse.postValue(Resource.error(response.errorBody().toString(),null))
                }
            }
    }
}