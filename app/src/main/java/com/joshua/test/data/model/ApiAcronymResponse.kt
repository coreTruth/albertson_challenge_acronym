package com.joshua.test.data.model

import com.google.gson.annotations.SerializedName

data class ApiAcronymResponse(
    @SerializedName("sf") val shortForm: String? = null,
    @SerializedName("lfs") val longFormsList: ArrayList<ApiLongForm>? = null
)