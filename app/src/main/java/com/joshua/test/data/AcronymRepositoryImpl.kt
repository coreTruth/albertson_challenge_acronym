package com.joshua.test.data

import com.joshua.test.data.model.ApiAcronymResponse
import com.joshua.test.domain.acronym.AcronymRepository
import com.joshua.test.domain.domainModel.LongForm
import javax.inject.Inject

class AcronymRepositoryImpl @Inject constructor(
    private val acronymApi: AcronymApi,
    private val acronymMapper: AcronymMapper,
) : AcronymRepository {
    override suspend fun getAcronyms(shortForm: String): List<LongForm> {
        val resp: List<ApiAcronymResponse> = acronymApi.getAcronyms(shortForm)
        return resp
            .firstOrNull()?.longFormsList?.map { acronymMapper.mapToDomain(it) } ?: emptyList()
    }
}