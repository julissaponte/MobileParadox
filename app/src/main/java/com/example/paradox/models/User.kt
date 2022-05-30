package com.example.paradox.models

import com.google.gson.annotations.SerializedName

class User (

    val id: Long,

    val firstname: String,
    val lastname: String,
    val email: String,
    val number: Long,
    val password: String,
    val document: String,
    val link :String,
    val other: String


)