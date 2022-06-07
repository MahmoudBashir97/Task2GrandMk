package com.mahmoud_bashir.task2grand.data.response.get_photos_album

data class GetPhotosAlbumResponseItem(
    val albumId: Int,
    val id: Int,
    val thumbnailUrl: String,
    val title: String,
    val url: String
)