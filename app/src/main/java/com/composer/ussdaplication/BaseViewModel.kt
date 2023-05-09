package com.composer.ussdaplication

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.composer.ussdaplication.domain.common.Resource
import com.composer.ussdaplication.presentation.common.UIListState
import com.composer.ussdaplication.presentation.common.UIObjectState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

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

                        Log.d("sdlfslkfjslkdflrjuh", "getList: ${it.data}")
                    }
                }
            }.launchIn(CoroutineScope(Dispatchers.IO))
        }

        return listState.asStateFlow()
    }

    fun <T> listFromDb(
        repositoryCall: suspend () -> Flow<List<T>>,
    ): Flow<UIListState<T>> {
        val listState = MutableStateFlow(UIListState<T>())

        viewModelScope.launch(Dispatchers.IO) {
            repositoryCall().onEach {
                listState.value = UIListState(data = it)
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