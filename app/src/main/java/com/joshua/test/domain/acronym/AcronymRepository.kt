package com.joshua.test.domain.acronym

import com.joshua.test.domain.domainModel.LongForm

interface AcronymRepository {
    suspend fun getAcronyms(shortForm: String): List<LongForm>
}