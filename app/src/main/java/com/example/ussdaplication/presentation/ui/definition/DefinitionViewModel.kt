package com.example.ussdaplication.presentation.ui.definition

import com.example.ussdaplication.BaseViewModel
import com.example.ussdaplication.domain.model.tarif.GetTariffModel
import com.example.ussdaplication.domain.repository.MainRepository
import com.example.ussdaplication.presentation.common.UIListState
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class DefinitionViewModel @Inject constructor(private val mainRepository: MainRepository) :
    BaseViewModel() {

    suspend fun getTariff(company: String): Flow<UIListState<GetTariffModel>> {
        return getList { mainRepository.getTariff(company) }
    }

}