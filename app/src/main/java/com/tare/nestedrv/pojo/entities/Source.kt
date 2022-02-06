package com.tare.nestedrv.pojo.entities


import com.google.gson.annotations.SerializedName

data class Source(
    @SerializedName("id")
    var id: Any? = null,
    @SerializedName("name")
    var name: String = ""
)