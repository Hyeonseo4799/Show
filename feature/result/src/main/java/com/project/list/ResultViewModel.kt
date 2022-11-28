package com.project.list

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.project.common.result.Result
import com.project.common.result.asResult
import com.project.data.repository.ResultRepository
import com.project.model.Show
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ResultViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val repository: ResultRepository
) : ViewModel() {
    private val title = savedStateHandle.get<String>("title")!!

    private val _resultUiState = MutableStateFlow<ResultUiState>(ResultUiState.Loading)
    val resultUiState: StateFlow<ResultUiState> = _resultUiState.asStateFlow()

    init {
        searchShow(title)
    }

    fun searchShow(title: String) {
        viewModelScope.launch {
            repository.getShows(title)
                .asResult()
                .collect { result ->
                    when (result) {
                        is Result.Error -> {
                            _resultUiState.value = ResultUiState.Error(result.exception?.message ?: "Unknown Error")
                        }
                        is Result.Loading -> {
                            _resultUiState.value = ResultUiState.Loading
                        }
                        is Result.Success -> {
                            val showResource = result.data
                            _resultUiState.value = ResultUiState.Success(showResource)
                        }
                    }
                }
        }
    }
}

sealed interface ResultUiState {
    data class Success(val shows: List<Show>) : ResultUiState
    data class Error(val message: String) : ResultUiState
    object Loading : ResultUiState
}