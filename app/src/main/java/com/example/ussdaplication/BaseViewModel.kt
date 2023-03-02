package com.example.ussdaplication

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ussdaplication.presentation.common.UIListState
import com.example.ussdaplication.presentation.common.UIObjectState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import uz.rounded.baqlajon.domain.common.Resource

abstract class BaseViewModel : ViewModel() {

    fun <T> getList(
        repositoryCall: suspend () -> Flow<Resource<List<T>>>,
    ): Flow<UIListState<T>> {
        val listState = MutableStateFlow(UIListState<T>())

        viewModelScope.launch(Dispatchers.IO) {
            repositoryCall().onEach {
                when (it) {
                    is Resource.Error -> {
                        listState.value = UIListState(it.message ?: "")
                    }
                    is Resource.Loading -> {
                        listState.value = UIListState(isLoading = true)
                    }
                    is Resource.Success -> {
                        listState.value = UIListState(data = it.data)
                    }
                }
            }.launchIn(CoroutineScope(Dispatchers.IO))
        }

        return listState.asStateFlow()
    }

    fun <T> getDataObject(
        repositoryCall: suspend () -> Flow<Resource<T>>,
    ): Flow<UIObjectState<T>> {
        val objectState = MutableStateFlow(UIObjectState<T>())

        viewModelScope.launch {
            repositoryCall().onEach {
                when (it) {
                    is Resource.Error -> {
                        objectState.value = UIObjectState(it.message ?: "")
                    }
                    is Resource.Loading -> {
                        objectState.value = UIObjectState(isLoading = true)
                    }
                    is Resource.Success -> {
                        objectState.value = UIObjectState(data = it.data)
                    }
                }
            }.launchIn(CoroutineScope(Dispatchers.IO))
        }

        return objectState.asStateFlow()
    }

}