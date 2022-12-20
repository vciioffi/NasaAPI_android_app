package com.example.theuniverseapp.apod.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.theuniverseapp.apod.domain.model.ApodModel
import com.example.theuniverseapp.apod.domain.usecases.GetApodListUc
import com.example.theuniverseapp.apod.domain.usecases.GetApodListWithDatesUc
import com.example.theuniverseapp.apod.domain.usecases.GetApodUc
import com.example.theuniverseapp.apod.domain.usecases.GetApodWithDateUc
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

data class ApodModelUiState(
    var apodModel: ApodModel? = null,
    var listApodModel: MutableList<ApodModel>? = null,

    )

@HiltViewModel
class ApodViewModel @Inject constructor(

    private val getApodUc: GetApodUc,
    private val getApodListUc: GetApodListUc,
    private val getApodListWithDatesUc: GetApodListWithDatesUc,
    private val getApodtWithDateUc: GetApodWithDateUc

) : ViewModel() {

    private val _uiState = MutableStateFlow(ApodModelUiState())
    val uiState: StateFlow<ApodModelUiState> = _uiState.asStateFlow()
    private var dateStart: Int = 30
    private var datEnd:Int  =15

    init {
        updateApodList()
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
     fun updateApodWithDate(date: String) {
        var newList = _uiState.value.listApodModel
         println("aaa"+ (newList?.size ?: 0))
         viewModelScope.launch {
             newList?.add(getApodtWithDateUc.invoke(date))

             _uiState.update {
                it.copy(
                    listApodModel = listOf<ApodModel>(getApodtWithDateUc.invoke(date)).toMutableList()
                )
             }
             println("www"+ (_uiState.value.listApodModel?.size ?: 0))
         }
    }

     fun updateApodList() {
        viewModelScope.launch {
            _uiState.update {
                it.copy(
                    listApodModel = getApodListUc.invoke().toMutableList()
                )
            }
        }
    }
    fun updateApodListPagination(){
        viewModelScope.launch {
            _uiState.update {
                it.copy(
                    listApodModel = getApodListWithDatesUc.invoke(dateStart, datEnd).toMutableList()
                )
            }
        }
    }
    }