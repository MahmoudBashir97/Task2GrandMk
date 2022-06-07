package com.mahmoud_bashir.task2grand.data.response.get_user_info

import com.squareup.moshi.Json

data class GetUserInfoResponse(
    @Json(name = "address")
    val address: Address,
    @Json(name = "company")
    val company: Company,
    @Json(name = "email")
    val email: String,
    @Json(name = "id")
    val id: Int,
    @Json(name = "name")
    val name: String,
    @Json(name = "phone")
    val phone: String,
    @Json(name = "username")
    val username: String,
    @Json(name = "website")
    val website: String
)