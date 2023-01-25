package com.joshua.test.domain.domainModel

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Variation(
    val longForm: String,
    val frequency: Int,
    val since: Int
): Parcelable