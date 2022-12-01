package com.project.tvmaze

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.project.common.result.Result
import com.project.common.result.asResult
import com.project.data.repository.DetailRepository
import com.project.model.Cast
import com.project.model.ShowResource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    repository: DetailRepository
) : ViewModel() {
    private val id = savedStateHandle.get<Int>("id")!!

    val detailUiState: StateFlow<DetailUiState> = detailUiStateStream(
        id = id,
        repository = repository
    )
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = DetailUiState.Loading
        )
}

private fun detailUiStateStream(
    id: Int,
    repository: DetailRepository
): Flow<DetailUiState> {
    val showResourceStream = repository.getShowResources(id)
    val crewStream = repository.getCrews(id)

    return combine(
        showResourceStream,
        crewStream,
        ::Pair
    )
        .asResult()
        .map { result ->
            when (result) {
                is Result.Error -> {
                    DetailUiState.Error(result.exception?.message ?: "Unknown Error")
                }
                is Result.Loading -> {
                    DetailUiState.Loading
                }
                is Result.Success -> {
                    val (showResources, crews) = result.data
                    DetailUiState.Success(showResource = showResources, casts = crews)
                }
            }
        }
}

sealed interface DetailUiState {
    data class Success(val showResource: ShowResource, val casts: List<Cast>) : DetailUiState
    data class Error(val message: String) : DetailUiState
    object Loading : DetailUiState
}