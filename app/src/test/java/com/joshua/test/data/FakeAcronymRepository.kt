package com.joshua.test.data

import com.joshua.test.domain.acronym.AcronymRepository
import com.joshua.test.domain.domainModel.LongForm

class FakeAcronymRepository(
    private val fakeDataSource: FakeDataSource,
    private val acronymMapper: AcronymMapper
) : AcronymRepository {

    override suspend fun getAcronyms(shortForm: String): List<LongForm> {
        val resp = fakeDataSource.getAcronyms(shortForm)
        return resp
            .firstOrNull()?.longFormsList?.map { acronymMapper.mapToDomain(it) } ?: emptyList()
    }
}