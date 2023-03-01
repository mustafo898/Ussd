package com.example.ussdaplication.presentation.ui.sms

import com.example.ussdaplication.BaseViewModel
import com.example.ussdaplication.domain.model.GetTypeModel
import com.example.ussdaplication.domain.model.sms.GetSmsModel
import com.example.ussdaplication.domain.repository.MainRepository
import com.example.ussdaplication.presentation.common.UIListState
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SmsViewModel @Inject constructor(private val mainRepository: MainRepository) :
    BaseViewModel() {

    suspend fun getType(): Flow<UIListState<GetTypeModel>> {
        return getList { mainRepository.getSmsType() }
    }

    suspend fun getSms(id: String, company: String): Flow<UIListState<GetSmsModel>> {
        return getList { mainRepository.getSms(id, company) }
    }

}