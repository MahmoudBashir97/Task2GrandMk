package com.mahmoud_bashir.task2grand.data.server

import com.mahmoud_bashir.task2grand.data.local.model.PhotosModel
import com.mahmoud_bashir.task2grand.data.response.get_albums.GetAlbumsResponseItem
import com.mahmoud_bashir.task2grand.data.response.get_photos_album.GetPhotosAlbumResponseItem
import com.mahmoud_bashir.task2grand.data.response.get_user_info.GetUserInfoResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiServer {

    @GET("users/{userId}")
   suspend fun getUserInfo(
        @Path("userId") userId:Int
   ):Response<GetUserInfoResponse>

   @GET("albums")
   suspend fun getAlbumsList(
       @Query("userId") userId: Int
   ):Response<List<GetAlbumsResponseItem>>


   @GET("photos")
   suspend fun getPhotosAlbumList(
       @Query("albumId") albumId: Int
   ):Response<List<PhotosModel>>


}