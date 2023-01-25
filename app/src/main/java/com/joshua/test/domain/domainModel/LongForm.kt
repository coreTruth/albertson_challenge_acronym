package com.joshua.test.domain.domainModel

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class LongForm(
    val longForm: String,
    val frequency: Int,
    val since: Int, //The year when the definition appeared for the first time in the corpus.
    val variations: List<Variation>
): Parcelable