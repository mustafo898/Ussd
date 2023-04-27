package com.composer.ussdaplication.presentation.ui.sms

import com.composer.ussdaplication.BaseViewModel
import com.composer.ussdaplication.domain.model.GetTypeModel
import com.composer.ussdaplication.domain.model.sms.GetSmsModel
import com.composer.ussdaplication.domain.repository.MainRepository
import com.composer.ussdaplication.presentation.common.UIListState
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SmsViewModel @Inject constructor(private val mainRepository: MainRepository) : BaseViewModel() {

    suspend fun getType(company: String): Flow<UIListState<GetTypeModel>> {
        return getList { mainRepository.getSmsType(company) }
    }

    suspend fun getSms(id: String, company: String): Flow<UIListState<GetSmsModel>> {
        return getList { mainRepository.getSms(id, company) }
    }

}