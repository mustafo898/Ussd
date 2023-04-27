package com.composer.ussdaplication.data.local.dao

import androidx.room.*
import com.composer.ussdaplication.data.local.models.minute.GetMinuteDb
import com.composer.ussdaplication.data.local.models.minute.GetMinuteTypeDb
import com.composer.ussdaplication.data.remote.dto.minute.GetMinuteDto
import com.composer.ussdaplication.data.remote.dto.minute.GetMinuteTypeDto

@Dao
interface MinuteDao {

    /**  MINUTE type CRUD */

    @Query("SELECT * FROM minuteTypeDb WHERE company = :company")
    fun getMinuteType(company: String): List<GetMinuteTypeDb>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun setMinuteType(users: List<GetMinuteTypeDb>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun setMinuteType(user: GetMinuteTypeDb)

    @Delete
    fun deleteMinuteType(user: GetMinuteTypeDb)

    @Query("DELETE FROM minuteTypeDb")
    fun deleteAllMinuteType()

    /**  MINUTE type CRUD */

    /**  MINUTE CRUD */

    @Query("SELECT * FROM minute WHERE typeId = :id AND company = :company")
    fun getMinuteByIDList(id: String, company: String): List<GetMinuteDb>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun setMinute(users: List<GetMinuteDb>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun setMinute(user: GetMinuteDb)

    @Delete
    fun deleteMinute(user: GetMinuteDb)

    @Query("DELETE FROM minute")
    fun deleteAllMinute()

    /**  MINUTE type CRUD */

}