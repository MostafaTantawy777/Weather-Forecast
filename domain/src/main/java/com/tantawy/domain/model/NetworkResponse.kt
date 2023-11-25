package com.tantawy.domain.model

import com.google.gson.annotations.SerializedName

open class BaseResponse {
    @SerializedName("message")
    var message: String? = null

    @SerializedName("cod")
    var code: Int = 0
}

class SingleBaseResponse<T> : BaseResponse() {
    @SerializedName("result")
    val result: T? = null
}

open class MultiBaseResponse<T> : BaseResponse() {
    @SerializedName("list")
    val result: ArrayList<T?> = ArrayList()
}