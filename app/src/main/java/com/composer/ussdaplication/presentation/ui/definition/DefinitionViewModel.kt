package com.composer.ussdaplication.presentation.ui.definition

import com.composer.ussdaplication.BaseViewModel
import com.composer.ussdaplication.domain.model.GetTypeModel
import com.composer.ussdaplication.domain.model.tarif.GetTariffModel
import com.composer.ussdaplication.domain.repository.MainRepository
import com.composer.ussdaplication.presentation.common.UIListState
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class DefinitionViewModel @Inject constructor(private val mainRepository: MainRepository) : BaseViewModel() {

    suspend fun getTariff(company: String,id:String): Flow<UIListState<GetTariffModel>> {
        return getList { mainRepository.getTariff(company,id) }
    }

    suspend fun getTariffType(company: String): Flow<UIListState<GetTypeModel>> {
        return getList { mainRepository.getTariffType(company) }
    }

}