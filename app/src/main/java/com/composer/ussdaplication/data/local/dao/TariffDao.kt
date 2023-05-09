package com.composer.ussdaplication.data.local.dao

import androidx.room.*
import com.composer.ussdaplication.data.local.models.internet.GetInternetDb
import com.composer.ussdaplication.data.local.models.internet.GetInternetTypeDb
import com.composer.ussdaplication.data.local.models.tariff.GetTariffDtoDb
import com.composer.ussdaplication.data.local.models.tariff.GetTariffTypeDtoDb
import com.composer.ussdaplication.data.remote.dto.tarif.GetTariffDto
import com.composer.ussdaplication.data.remote.dto.tarif.GetTariffTypeDto

@Dao
interface TariffDao {

    /**  TARIFF CRUD */

    @Query("SELECT * FROM tariff where company = :company and typeId =:id")
    fun getTariff(company:String,id:String): List<GetTariffDtoDb>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun setTariff(users: List<GetTariffDtoDb>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun setTariff(user: GetTariffDtoDb)

    @Delete
    fun deleteTariff(user: GetTariffDtoDb)

    @Query("DELETE FROM tariff")
    fun deleteAllTariff()

    @Query("DELETE FROM tariff WHERE typeId = :typeId and company = :company")
    fun deleteAllTariff(typeId:String, company: String)

    /**  TARIFF CRUD */

    /**  TARIFF type  CRUD */

    @Query("SELECT * FROM tariff_type WHERE company = :company")
    fun getTariffType(company: String): List<GetTariffTypeDtoDb>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun setTariffType(users: List<GetTariffTypeDtoDb>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun setTariffType(user: GetTariffTypeDtoDb)

    @Delete
    fun deleteTariffType(user: GetTariffTypeDtoDb)

    @Query("DELETE FROM internetTypeDb where company = :company")
    fun deleteAllTariffType(company: String)

    /**  TARIFF type CRUD */
}