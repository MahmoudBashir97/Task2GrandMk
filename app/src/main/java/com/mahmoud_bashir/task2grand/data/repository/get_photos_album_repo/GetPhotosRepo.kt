package com.mahmoud_bashir.task2grand.data.repository.get_photos_album_repo

import com.mahmoud_bashir.task2grand.data.local.model.PhotosModel
import com.mahmoud_bashir.task2grand.data.response.get_photos_album.GetPhotosAlbumResponseItem
import com.mahmoud_bashir.task2grand.data.server.ApiServer
import retrofit2.Response
import javax.inject.Inject

class GetPhotosRepo @Inject constructor(val apiServer: ApiServer) {

    suspend fun getPhotosAlbumList(albumId:Int):Response<List<PhotosModel>> =
        apiServer.getPhotosAlbumList(albumId)
}