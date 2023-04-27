package com.composer.ussdaplication.data.mapper

import com.composer.ussdaplication.data.local.models.internet.GetInternetDb
import com.composer.ussdaplication.data.local.models.internet.GetInternetTypeDb
import com.composer.ussdaplication.data.local.models.minute.GetMinuteDb
import com.composer.ussdaplication.data.local.models.minute.GetMinuteTypeDb
import com.composer.ussdaplication.data.local.models.sms.GetSmsDb
import com.composer.ussdaplication.data.local.models.sms.GetSmsTypeDb
import com.composer.ussdaplication.data.local.models.tariff.GetTariffDtoDb
import com.composer.ussdaplication.data.local.models.tariff.GetTariffTypeDtoDb
import com.composer.ussdaplication.data.local.models.ussd.GetUssdDtoDb
import com.composer.ussdaplication.data.remote.dto.LanguageDto
import com.composer.ussdaplication.data.remote.dto.ads.GetNewsDto
import com.composer.ussdaplication.data.remote.dto.internet.GetInternetDto
import com.composer.ussdaplication.data.remote.dto.internet.GetInternetTypeDto
import com.composer.ussdaplication.data.remote.dto.minute.GetMinuteDto
import com.composer.ussdaplication.data.remote.dto.minute.GetMinuteTypeDto
import com.composer.ussdaplication.data.remote.dto.sms.GetSmsDto
import com.composer.ussdaplication.data.remote.dto.sms.GetSmsTypeDto
import com.composer.ussdaplication.data.remote.dto.tarif.GetTariffDto
import com.composer.ussdaplication.data.remote.dto.tarif.GetTariffTypeDto
import com.composer.ussdaplication.data.remote.dto.ussd.GetUssdDto
import com.composer.ussdaplication.domain.ads.GetNewsModel
import com.composer.ussdaplication.domain.model.GetTypeModel
import com.composer.ussdaplication.domain.model.LanguageModel
import com.composer.ussdaplication.domain.model.internet.GetInternetModel
import com.composer.ussdaplication.domain.model.minute.GetMinuteModel
import com.composer.ussdaplication.domain.model.sms.GetSmsModel
import com.composer.ussdaplication.domain.model.tarif.GetTariffModel
import com.composer.ussdaplication.domain.model.ussd.GetUssdModel

fun GetInternetTypeDto.toModel(): GetTypeModel {
    return GetTypeModel(_id, company, createdAt, name.toModel())
}

fun LanguageDto.toModel(): LanguageModel {
    return LanguageModel(_id, en, ru, uz)
}

fun GetMinuteTypeDto.toModel(): GetTypeModel {
    return GetTypeModel(_id, company, createdAt, name.toModel())
}

fun GetSmsTypeDto.toModel(): GetTypeModel {
    return GetTypeModel(_id, company, createdAt, name.toModel())
}

fun GetInternetDto.toModel(): GetInternetModel {
    return GetInternetModel(
        _id,
        company,
        description?.toModel(),
        duration.toModel(),
        name.toModel(),
        price,
        turnOff,
        turnOn,
        typeId,
        checkLimit
    )
}

fun GetMinuteDto.toModel(): GetMinuteModel {
    return GetMinuteModel(
        _id,
        company,
        description?.toModel(),
        duration.toModel(),
        name.toModel(),
        price,
        turnOff,
        turnOn,
        typeId,
        checkLimit
    )
}

fun GetSmsDto.toModel(): GetSmsModel {
    return GetSmsModel(
        _id,
        company,
        description?.toModel(),
        duration.toModel(),
        name.toModel(),
        price,
        turnOff,
        turnOn,
        typeId,
        checkLimit
    )
}

fun GetTariffDto.toModel(): GetTariffModel {
    return GetTariffModel(
        _id,
        checkLimit,
        code,
        company,
        description?.toModel(),
        internet.toModel(),
        internetPrice,
        minute.toModel(),
        minutePrice,
        name.toModel(),
        outInternet?.toModel(),
        outMinute?.toModel(),
        price,
        sms.toModel(),
        smsPrice,typeId
    )
}

fun GetTariffDtoDb.toModel(): GetTariffModel {
    return GetTariffModel(
        _id,
        checkLimit,
        code,
        company,
        LanguageModel(descriptionId, descriptionEn, descriptionRu, descriptionUz),
        LanguageModel(internetId, internetEn, internetRu, internetUz),
        internetPrice,
        LanguageModel(minuteId, minuteEn, minuteRu, minuteUz),
        minutePrice,
        LanguageModel(nameId, nameEn, nameRu, nameUz),
        LanguageModel(outInternetId, outInternetEn, outInternetRu, outInternetUz),
        LanguageModel(outMinuteId, outMinuteEn, outMinuteRu, outMinuteUz), price,
        LanguageModel(smsId, smsEn, smsRu, smsUz),
        smsPrice,typeId
    )
}

fun GetTariffDto.toDbModel(): GetTariffDtoDb {
    return GetTariffDtoDb(
        _id,
        checkLimit,
        code,
        company,
        description?._id ?: "",
        description?.uz ?: "",
        description?.ru ?: "",
        description?.en ?: "",
        internet._id,
        internet.uz,
        internet.ru,
        internet.en,
        internetPrice,
        minute._id,
        minute.uz,
        minute.ru,
        minute.en,
        minutePrice,
        name._id,
        name.uz,
        name.ru,
        name.en,
        outInternet?._id ?: "",
        outInternet?.uz ?: "",
        outInternet?.ru ?: "",
        outInternet?.en ?: "",
        outMinute?._id ?: "",
        outMinute?.uz ?: "",
        outMinute?.ru ?: "",
        outMinute?.en ?: "",
        price, sms._id, sms.uz, sms.ru, sms.en, smsPrice,typeId
    )
}

fun GetUssdDto.toDbModel(): GetUssdDtoDb {
    return GetUssdDtoDb(
        _id,
        code,
        company,
        description._id,
        description.uz,
        description.ru,
        description.en,
        name._id,
        name.uz,
        name.ru,
        name.en
    )
}

fun GetUssdDtoDb.toModel(): GetUssdModel {
    return GetUssdModel(
        _id, code, company,
        LanguageModel(descriptionId, descriptionEn, descriptionRu, descriptionUz),
        LanguageModel(nameId, nameEn, nameRu, nameUz)
    )
}

fun GetNewsDto.toModel(): GetNewsModel {
    return GetNewsModel(_id, company, image, url)
}

fun GetUssdDto.toModel(): GetUssdModel {
    return GetUssdModel(_id, code, company, description.toModel(), name.toModel())
}

fun GetMinuteDto.toDbModel(): GetMinuteDb {
    return GetMinuteDb(
        _id,
        company,
        description?.en ?: "",
        description?.ru ?: "",
        description?.uz ?: "",
        duration.en,
        duration.uz,
        duration.ru,
        name.uz,
        name.ru,
        name.en,
        duration._id,
        description?._id ?: "",
        name._id,
        price,
        turnOff,
        turnOn,
        typeId,
        checkLimit
    )
}

fun GetInternetDto.toDbModel(): GetInternetDb {
    return GetInternetDb(
        _id,
        company,
        description?.en ?: "",
        description?.ru ?: "",
        description?.uz ?: "",
        duration.en,
        duration.uz,
        duration.ru,
        name.uz,
        name.ru,
        name.en,
        duration._id,
        description?._id ?: "",
        name._id,
        price,
        turnOff,
        turnOn,
        typeId,
        checkLimit
    )
}

fun GetSmsDto.toDbModel(): GetSmsDb {
    return GetSmsDb(
        _id,
        company,
        description?.en ?: "",
        description?.ru ?: "",
        description?.uz ?: "",
        duration.en,
        duration.uz,
        duration.ru,
        name.uz,
        name.ru,
        name.en,
        duration._id,
        description?._id ?: "",
        name._id,
        price,
        turnOff,
        turnOn,
        typeId,
        checkLimit
    )
}

fun GetSmsDb.toModel(): GetSmsModel {
    return GetSmsModel(
        _id,
        company,
        LanguageModel(descriptionId, descriptionEn, descriptionRu, descriptionUz),
        LanguageModel(durationId, durationEn, durationRu, durationUz),
        LanguageModel(nameId, nameEn, nameRu, nameUz),
        price,
        turnOff,
        turnOn,
        typeId,
        checkLimit
    )
}

fun GetInternetDb.toModel(): GetInternetModel {
    return GetInternetModel(
        _id,
        company,
        LanguageModel(descriptionId, descriptionEn, descriptionRu, descriptionUz),
        LanguageModel(durationId, durationEn, durationRu, durationUz),
        LanguageModel(nameId, nameEn, nameRu, nameUz),
        price,
        turnOff,
        turnOn,
        typeId,
        checkLimit
    )
}

fun GetMinuteDb.toModel(): GetMinuteModel {
    return GetMinuteModel(
        _id,
        company,
        LanguageModel(descriptionId, descriptionEn, descriptionRu, descriptionUz),
        LanguageModel(durationId, durationEn, durationRu, durationUz),
        LanguageModel(nameId, nameEn, nameRu, nameUz),
        price,
        turnOff,
        turnOn,
        typeId,
        checkLimit
    )
}

fun GetMinuteTypeDto.toDbModel(): GetMinuteTypeDb {
    return GetMinuteTypeDb(_id, company, createdAt, name.uz, name.ru, name.en, name._id)
}

fun GetTariffTypeDto.toDbModel(): GetTariffTypeDtoDb {
    return GetTariffTypeDtoDb(_id, company, createdAt, name.uz, name.ru, name.en, name._id)
}

fun GetSmsTypeDto.toDbModel(): GetSmsTypeDb {
    return GetSmsTypeDb(_id, company, createdAt, name.uz, name.ru, name.en, name._id)
}

fun GetInternetTypeDto.toDbModel(): GetInternetTypeDb {
    return GetInternetTypeDb(_id, company, createdAt, name.uz, name.ru, name.en, name._id)
}

fun GetInternetTypeDb.toModel(): GetTypeModel {
    return GetTypeModel(_id, company, createdAt, LanguageModel(nameId, nameEn, nameRu, nameUz))
}

fun GetTariffTypeDtoDb.toModel(): GetTypeModel {
    return GetTypeModel(_id, company, createdAt, LanguageModel(nameId, nameEn, nameRu, nameUz))
}

fun GetSmsTypeDb.toModel(): GetTypeModel {
    return GetTypeModel(_id, company, createdAt, LanguageModel(nameId, nameEn, nameRu, nameUz))
}

fun GetMinuteTypeDb.toModel(): GetTypeModel {
    return GetTypeModel(_id, company, createdAt, LanguageModel(nameId, nameEn, nameRu, nameUz))
}