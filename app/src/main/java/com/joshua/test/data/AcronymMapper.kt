package com.joshua.test.data

import com.joshua.test.data.model.ApiLongForm
import com.joshua.test.data.model.ApiVariation
import com.joshua.test.domain.domainModel.LongForm
import com.joshua.test.domain.domainModel.Variation
import javax.inject.Inject

class AcronymMapper @Inject constructor() {
    fun mapToDomain(apiLongForm: ApiLongForm): LongForm {
        return LongForm(
            longForm = apiLongForm.longForm ?: "",
            frequency = apiLongForm.frequency ?: 0,
            since = apiLongForm.since ?: 1900,
            variations = apiLongForm.vars?.map { mapToDomain(it) } ?: emptyList(),
        )
    }

    private fun mapToDomain(apiVariation: ApiVariation): Variation {
        return Variation(
            longForm = apiVariation.longForm ?: "",
            frequency = apiVariation.frequency ?: 0,
            since = apiVariation.since ?: 1900,
        )
    }
}