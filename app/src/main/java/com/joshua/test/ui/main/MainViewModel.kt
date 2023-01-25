package com.joshua.test.ui.main

import androidx.lifecycle.*
import com.joshua.test.domain.domainModel.LongForm
import com.joshua.test.domain.usecase.GetAcronymUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getAcronymUseCase: GetAcronymUseCase
) : ViewModel() {
    val uiState: LiveData<MainUiState> get() = _uiState
    private val _uiState = MutableLiveData<MainUiState>()
    var acronymQuery = MutableLiveData<String>()
    var validationError: LiveData<String?> = Transformations.map(acronymQuery) {
        if (it.isNullOrBlank()) {
            "Enter acronym"
        } else null
    }

    fun getAcronyms() {
        if (_uiState.value !is MainUiState.Loading) {
            _uiState.value = MainUiState.Loading
        }
        viewModelScope.launch {
            kotlin.runCatching {
                getAcronymUseCase.execute(acronymQuery.value!!)
            }.onSuccess {
                _uiState.value = MainUiState.Success(it)
            }.onFailure {
                _uiState.value = MainUiState.Error(it.message ?: "Unknown Error")
            }
        }
    }

    sealed class MainUiState {
        object Loading : MainUiState()
        data class Error(val msg: String) : MainUiState()
        data class Success(val longFormList: List<LongForm>) : MainUiState()
    }
}