package com.composer.ussdaplication.presentation.ui.ussd

import com.composer.ussdaplication.BaseViewModel
import com.composer.ussdaplication.domain.model.ussd.GetUssdModel
import com.composer.ussdaplication.domain.repository.MainRepository
import com.composer.ussdaplication.presentation.common.UIListState
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UssdViewModel @Inject constructor(private val mainRepository: MainRepository) : BaseViewModel() {
    fun getUssd(company: String): Flow<UIListState<GetUssdModel>> {
        return getList { mainRepository.getUssd(company) }
    }
}