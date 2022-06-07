package com.mahmoud_bashir.task2grand.data.repository.get_albums_repo

import com.mahmoud_bashir.task2grand.data.response.get_albums.GetAlbumsResponseItem
import com.mahmoud_bashir.task2grand.data.server.ApiServer
import retrofit2.Response
import javax.inject.Inject

class GetAlbumsRepository @Inject constructor(val apiServer: ApiServer) {

    suspend fun getAlbumsList(userId:Int):Response<List<GetAlbumsResponseItem>> =
        apiServer.getAlbumsList(userId)
}