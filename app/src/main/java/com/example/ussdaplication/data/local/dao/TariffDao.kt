package com.example.ussdaplication.data.local.dao

import androidx.room.*
import com.example.ussdaplication.data.remote.dto.tarif.GetTariffDto

@Dao
interface TariffDao {

    /**  TARIFF CRUD */

    @Query("SELECT * FROM tariff")
    fun getTariff(): List<GetTariffDto>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun setTariff(users: List<GetTariffDto>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun setTariff(user: GetTariffDto)

    @Delete
    fun deleteTariff(user: GetTariffDto)

    @Query("DELETE FROM tariff")
    fun deleteAllTariff()

    /**  TARIFF CRUD */

}