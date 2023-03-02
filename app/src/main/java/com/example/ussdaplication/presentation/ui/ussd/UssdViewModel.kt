package com.example.ussdaplication.presentation.ui.ussd

import com.example.ussdaplication.BaseViewModel
import com.example.ussdaplication.domain.model.ussd.GetUssdModel
import com.example.ussdaplication.domain.repository.MainRepository
import com.example.ussdaplication.presentation.common.UIListState
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UssdViewModel @Inject constructor(private val mainRepository: MainRepository) : BaseViewModel() {
    fun getUssd(company: String): Flow<UIListState<GetUssdModel>> {
        return getList { mainRepository.getUssd(company) }
    }
}