package com.example.theuniverseapp.apod.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.theuniverseapp.apod.domain.model.ApodModel
import com.example.theuniverseapp.apod.domain.usecases.GetApodUc
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

data class ApodModelUiState(
    val apodModel: ApodModel? = null
)

//TODO: Inject usecase
@HiltViewModel
class ApodViewModel @Inject constructor(

    private val getApodUc: GetApodUc

) : ViewModel() {

    private val _uiState = MutableStateFlow(ApodModelUiState())
    val uiState: StateFlow<ApodModelUiState> = _uiState.asStateFlow()

    init {
        updateApod()
    }

    private fun updateApod() {
        viewModelScope.launch {
            _uiState.update {
                it.copy(
                    apodModel = getApodUc.invoke()
                )
            }
        }
    }
    }