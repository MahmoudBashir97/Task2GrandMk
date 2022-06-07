package com.mahmoud_bashir.task2grand.data.repository.localRepository

import com.mahmoud_bashir.task2grand.data.local.PhotosDatabase
import com.mahmoud_bashir.task2grand.data.local.model.PhotosModel
import javax.inject.Inject

class LocalRepository @Inject constructor(val db: PhotosDatabase) {

    suspend fun insertToLocal(photosModelList: List<PhotosModel>) = db.dao().insertPhotos(photosModelList)

    suspend fun getAllPhotosFromLocal():List<PhotosModel> = db.dao().getAllUsers()

    suspend fun searchOnPhotos(query:String):List<PhotosModel> = db.dao().searchOnPhotos(query)

}