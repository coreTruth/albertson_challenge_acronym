package com.joshua.test.data

import com.joshua.test.data.model.ApiAcronymResponse
import retrofit2.http.*

const val DICTIONARY_ENDPOINT = "software/acromine/dictionary.py"

interface AcronymApi {
    @GET(DICTIONARY_ENDPOINT)
    suspend fun getAcronyms(
        @Query(value = "sf") shortFrom: String
    ): List<ApiAcronymResponse>
}