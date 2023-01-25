package com.joshua.test.vm

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.joshua.test.data.AcronymMapper
import com.joshua.test.data.FakeAcronymRepository
import com.joshua.test.data.FakeDataSource
import com.joshua.test.domain.usecase.GetAcronymUseCase
import com.joshua.test.ui.main.MainViewModel
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class MainViewModelTest {

    @get:Rule
    val dispatcherRule = MainDispatcherRule()

    private lateinit var mainViewModel: MainViewModel

    private lateinit var getAcronymUseCase: GetAcronymUseCase
    private lateinit var fakeAcronymRepository: FakeAcronymRepository

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @Before
    fun setup() {
        val fakeDataSource = FakeDataSource()
        val acronymMapper = AcronymMapper()
        fakeAcronymRepository = FakeAcronymRepository(fakeDataSource, acronymMapper)
        getAcronymUseCase = GetAcronymUseCase(fakeAcronymRepository, dispatcherRule.testDispatcher)
        mainViewModel = MainViewModel(getAcronymUseCase)
    }

    @Test
    fun checkIfUserDoesNotEnterQueryThenValidationError() {
        val errorStr = "Enter acronym"
        mainViewModel.acronymQuery.value = null
        val actualError: String? = mainViewModel.validationError.getOrAwaitValue()
        Assert.assertNotNull(actualError)
        Assert.assertEquals(errorStr, actualError)
    }

    @Test
    fun checkIfUserEnterQueryThenNoValidationError() {
        mainViewModel.acronymQuery.value = "WWW"
        val actualError: String? = mainViewModel.validationError.getOrAwaitValue()
        Assert.assertNull(actualError)
    }

    @Test
    fun checkUiStateSuccessIfApicalledSuccessfully() = runTest {
        mainViewModel.acronymQuery.value = "IBM"
        mainViewModel.getAcronyms()
        val mainUiState: MainViewModel.MainUiState = mainViewModel.uiState.getOrAwaitValue()
        Assert.assertTrue(mainUiState is MainViewModel.MainUiState.Success)
    }

    @Test
    fun checkUiStateErrorWhenNoQueryProvided() = runTest {
        mainViewModel.acronymQuery.value = null
        mainViewModel.getAcronyms()
        val mainUiState: MainViewModel.MainUiState = mainViewModel.uiState.getOrAwaitValue()
        Assert.assertTrue(mainUiState is MainViewModel.MainUiState.Error)
    }
}