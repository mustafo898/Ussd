package com.example.ussdaplication.presentation.ui.minute

import com.example.ussdaplication.BaseViewModel
import com.example.ussdaplication.domain.model.GetTypeModel
import com.example.ussdaplication.domain.model.minute.GetMinuteModel
import com.example.ussdaplication.domain.repository.MainRepository
import com.example.ussdaplication.presentation.common.UIListState
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MinuteViewModel @Inject constructor(private val mainRepository: MainRepository) : BaseViewModel() {

    suspend fun getType(): Flow<UIListState<GetTypeModel>> {
        return getList { mainRepository.getMinuteType() }
    }

    suspend fun getMinute(id: String, company: String): Flow<UIListState<GetMinuteModel>> {
        return getList { mainRepository.getMinute(id, company) }
    }

}