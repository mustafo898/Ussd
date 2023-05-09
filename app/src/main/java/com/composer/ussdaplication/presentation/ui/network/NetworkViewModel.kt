package com.composer.ussdaplication.presentation.ui.network

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.composer.ussdaplication.BaseViewModel
import com.composer.ussdaplication.domain.common.Resource
import com.composer.ussdaplication.domain.model.GetTypeModel
import com.composer.ussdaplication.domain.model.internet.GetInternetModel
import com.composer.ussdaplication.domain.repository.MainRepository
import com.composer.ussdaplication.presentation.common.UIListState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class NetworkViewModel @Inject constructor(private val mainRepository: MainRepository) :
    BaseViewModel() {

    suspend fun getTypeDb(company: String): Flow<UIListState<GetTypeModel>> {
        val list = MutableStateFlow(UIListState<GetTypeModel>())

        viewModelScope.launch(Dispatchers.IO) {
            mainRepository.getInternetTypeDb(company).collect {
                when (it) {
                    is Resource.Error -> {
                        Log.d(
                            "sdlfslkfjslkdflrjuh internet",
                            "getType: type ERROR----   ${it.message}"
                        )
                        list.value = UIListState(it.message ?: "")
                    }
                    is Resource.Loading -> {
                        list.value = UIListState(isLoading = true)
                    }
                    is Resource.Success -> {
                        Log.d("sdlfslkfjslkdflrjuh internet", "getType: type ----   ${it.data}")
                        list.value = UIListState(data = it.data)

                    }
                }
            }
        }

        return list
    }

    suspend fun getInternet(id: String, company: String): Flow<UIListState<GetInternetModel>> {
        val list = MutableStateFlow(UIListState<GetInternetModel>())

        viewModelScope.launch(Dispatchers.IO) {
            mainRepository.getInternetDb(id, company).collect {
                when (it) {
                    is Resource.Error -> {
                        Log.d("sdlfslkfjslkdflrjuh internet", "getList: Error ------ ${it.message}")
                        list.value = UIListState(it.message ?: "")
                    }
                    is Resource.Loading -> {
                        list.value = UIListState(isLoading = true)
                    }
                    is Resource.Success -> {
                        Log.d("sdlfslkfjslkdflrjuh internet", "getList: Success ------ ${it.data}")
                        list.value = UIListState(data = it.data)
                    }
                }
            }
        }

        return list
    }

}