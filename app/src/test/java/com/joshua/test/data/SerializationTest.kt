package com.joshua.test.data

import com.joshua.test.domain.domainModel.LongForm
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Test

class SerializationTest {

    @Test
    fun check_IBM_query() = runTest {
        val query = "IBM"
        val expectedResult = 11
        val fakeDataSource = FakeDataSource()
        val acronymMapper = AcronymMapper()
        val fakeAcronymRepository = FakeAcronymRepository(fakeDataSource, acronymMapper)
        val acronyms: List<LongForm> = fakeAcronymRepository.getAcronyms(query)
        Assert.assertTrue(acronyms.size == expectedResult)
    }
}