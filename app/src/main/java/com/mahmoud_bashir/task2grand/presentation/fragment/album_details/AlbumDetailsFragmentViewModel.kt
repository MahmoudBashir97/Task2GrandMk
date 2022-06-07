package com.mahmoud_bashir.task2grand.presentation.fragment.album_details

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.mahmoud_bashir.task2grand.data.local.model.PhotosModel
import com.mahmoud_bashir.task2grand.data.repository.get_photos_album_repo.GetPhotosRepo
import com.mahmoud_bashir.task2grand.data.repository.localRepository.LocalRepository
import com.mahmoud_bashir.task2grand.data.response.get_photos_album.GetPhotosAlbumResponseItem
import com.mahmoud_bashir.task2grand.utilities.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AlbumDetailsFragmentViewModel @Inject constructor(app:Application,
                                                        val getPhotosRepo:GetPhotosRepo
                                                        ,val localRepo: LocalRepository ): AndroidViewModel(app) {

    val photosList:MutableLiveData<Resource<List<PhotosModel>>> = MutableLiveData()


    fun getPhotosList(albumId:Int) = viewModelScope.launch {
        getPhotosRepo.getPhotosAlbumList(albumId)
            .also { response ->
                val body = response.body()
                if (body != null){
                localRepo.insertToLocal(body)
                }
            }
    }

    fun getAllPhotos()=viewModelScope.launch {
        val photos = localRepo.getAllPhotosFromLocal()
        if (photos.isNotEmpty()){
            photosList.postValue(Resource.success(photos))
        }else photosList.postValue(Resource.error("is Empty , No Data",null))
        Log.d("GettingPhotos: ","size: ${photos.size}")
    }

    fun searchOnPhotos(query:String)=viewModelScope.launch {
        val mlist = localRepo.searchOnPhotos("%$query%")
        if (mlist.isNotEmpty()){
            photosList.postValue(Resource.success(mlist))
        }else photosList.postValue(Resource.error("is Empty , No Data",null))
        Log.d("SearchQ: ","size: ${mlist.size}")
    }
}