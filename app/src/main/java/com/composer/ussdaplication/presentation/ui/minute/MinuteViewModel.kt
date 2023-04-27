package com.composer.ussdaplication.presentation.ui.minute

import com.composer.ussdaplication.BaseViewModel
import com.composer.ussdaplication.domain.model.GetTypeModel
import com.composer.ussdaplication.domain.model.minute.GetMinuteModel
import com.composer.ussdaplication.domain.repository.MainRepository
import com.composer.ussdaplication.presentation.common.UIListState
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MinuteViewModel @Inject constructor(private val mainRepository: MainRepository) :
    BaseViewModel() {

    suspend fun getType(company: String): Flow<UIListState<GetTypeModel>> {
        return getList { mainRepository.getMinuteType(company) }
    }

    suspend fun getMinute(id: String, company: String): Flow<UIListState<GetMinuteModel>> {
        return getList { mainRepository.getMinute(id, company) }
    }

}