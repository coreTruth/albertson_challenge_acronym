package com.joshua.test.domain.usecase

import com.joshua.test.di.DefaultDispatcher
import com.joshua.test.domain.SuspendUseCase
import com.joshua.test.domain.acronym.AcronymRepository
import com.joshua.test.domain.domainModel.LongForm
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

class GetAcronymUseCase @Inject constructor(
    private val acronymRepository: AcronymRepository,
    @DefaultDispatcher private val dispatcher: CoroutineDispatcher
) : SuspendUseCase<String, List<LongForm>>(dispatcher) {
    override suspend fun execute(i: String): List<LongForm> {
        return acronymRepository.getAcronyms(i)
    }
}