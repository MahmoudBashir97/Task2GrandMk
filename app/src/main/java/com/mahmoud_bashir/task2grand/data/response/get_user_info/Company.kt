package com.mahmoud_bashir.task2grand.data.response.get_user_info

import com.squareup.moshi.Json

data class Company(
    @Json(name = "bs")
    val bs: String,
    @Json(name = "catchPhrase")
    val catchPhrase: String,
    @Json(name = "name")
    val name: String
)