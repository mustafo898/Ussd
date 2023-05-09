package com.composer.ussdaplication.data.local.dao

import androidx.room.*
import com.composer.ussdaplication.data.local.models.minute.GetMinuteDb
import com.composer.ussdaplication.data.local.models.minute.GetMinuteTypeDb

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

    @Query("DELETE FROM minuteTypeDb WHERE company = :company")
    fun deleteMinuteType(company: String)

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

    @Query("DELETE FROM minute WHERE typeId = :typeId and company = :company")
    fun deleteAllMinute(typeId: String, company: String)

    /**  MINUTE type CRUD */

}