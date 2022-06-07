package com.mahmoud_bashir.task2grand.data.response.get_user_info

import com.squareup.moshi.Json

data class Geo(
    @Json(name = "lat")
    val lat: String,
    @Json(name = "lng")
    val lng: String
)