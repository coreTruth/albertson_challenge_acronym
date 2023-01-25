package com.joshua.test.data.model

import com.google.gson.annotations.SerializedName

data class ApiVariation(
  @SerializedName("lf") var longForm: String? = null,
  @SerializedName("freq") var frequency: Int? = null,
  @SerializedName("since") var since: Int? = null
)