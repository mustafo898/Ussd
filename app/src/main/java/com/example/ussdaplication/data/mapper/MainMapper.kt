package com.example.ussdaplication.data.mapper

import com.example.ussdaplication.data.remote.dto.GetTypeDto
import com.example.ussdaplication.data.remote.dto.ads.GetNewsDto
import com.example.ussdaplication.data.remote.dto.internet.GetInternetDto
import com.example.ussdaplication.data.remote.dto.minute.GetMinuteDto
import com.example.ussdaplication.data.remote.dto.sms.GetSmsDto
import com.example.ussdaplication.data.remote.dto.tarif.GetTariffDto
import com.example.ussdaplication.data.remote.dto.ussd.GetUssdDto
import com.example.ussdaplication.domain.ads.GetNewsModel
import com.example.ussdaplication.domain.model.GetTypeModel
import com.example.ussdaplication.domain.model.internet.GetInternetModel
import com.example.ussdaplication.domain.model.minute.GetMinuteModel
import com.example.ussdaplication.domain.model.sms.GetSmsModel
import com.example.ussdaplication.domain.model.tarif.GetTariffModel
import com.example.ussdaplication.domain.model.ussd.GetUssdModel

fun GetTypeDto.toModel(): GetTypeModel {
    return GetTypeModel(_id, createdAt, name)
}

fun GetInternetDto.toModel(): GetInternetModel {
    return GetInternetModel(_id, code, company, duration, name, price, typeId)
}

fun GetMinuteDto.toModel(): GetMinuteModel {
    return GetMinuteModel(_id, code, company, price, typeId)
}

fun GetSmsDto.toModel(): GetSmsModel {
    return GetSmsModel(_id, code, company, duration, name, price, typeId)
}

fun GetTariffDto.toModel(): GetTariffModel {
    return GetTariffModel(_id, code, company, duration, internet, minute, name, price, sms)
}

fun GetNewsDto.toModel(): GetNewsModel {
    return GetNewsModel(_id, company, image, url)
}

fun GetUssdDto.toModel(): GetUssdModel {
    return GetUssdModel(_id, code, company, name)
}