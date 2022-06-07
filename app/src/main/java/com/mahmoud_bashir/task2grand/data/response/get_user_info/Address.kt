package com.mahmoud_bashir.task2grand.data.response.get_user_info

import com.squareup.moshi.Json

data class Address(
    @Json(name = "city")
    val city: String,
    @Json(name = "geo")
    val geo: Geo,
    @Json(name = "street")
    val street: String,
    @Json(name = "suite")
    val suite: String,
    @Json(name = "zipcode")
    val zipcode: String
)