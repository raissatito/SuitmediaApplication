package com.suitmedia.suitmediaapp.data

import com.google.gson.annotations.SerializedName

data class UserResponse (
    @field:SerializedName("data")
    val data: List<UserItem>
)

data class UserItem (
    @field:SerializedName("id")
    val id: Int,

    @field:SerializedName("email")
    val email: String,

    @field:SerializedName("first_name")
    val firstName: String,

    @field:SerializedName("last_name")
    val lastName: String,

    @field:SerializedName("avatar")
    val avatar: String
)