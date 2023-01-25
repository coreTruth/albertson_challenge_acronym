package com.joshua.test.data.model

import com.google.gson.annotations.SerializedName

data class ApiLongForm(
    @SerializedName("lf") var longForm: String? = null,
    @SerializedName("freq") var frequency: Int? = null,
    @SerializedName("since") var since: Int? = null, //The year when the definition appeared for the first time in the corpus.
    @SerializedName("vars") var vars: ArrayList<ApiVariation>? = null
)