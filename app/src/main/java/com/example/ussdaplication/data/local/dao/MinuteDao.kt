package com.example.ussdaplication.data.local.dao

import androidx.room.*
import com.example.ussdaplication.data.remote.dto.minute.GetMinuteDto
import com.example.ussdaplication.data.remote.dto.minute.GetMinuteTypeDto

@Dao
interface MinuteDao {

    /**  MINUTE type CRUD */

    @Query("SELECT * FROM minuteTypeDto")
    fun getMinuteType(): List<GetMinuteTypeDto>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun setMinuteType(users: List<GetMinuteTypeDto>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun setMinuteType(user: GetMinuteTypeDto)

    @Delete
    fun deleteMinuteType(user: GetMinuteTypeDto)

    @Query("DELETE FROM minuteTypeDto")
    fun deleteAllMinuteType()

    /**  MINUTE type CRUD */

    /**  MINUTE CRUD */

    @Query("SELECT * FROM minute")
    fun getMinute(): List<GetMinuteDto>

    @Query("SELECT * FROM minute WHERE typeId = :id AND company = :company")
    fun getMinuteByIDList(id: String, company: String): List<GetMinuteDto>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun setMinute(users: List<GetMinuteDto>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun setMinute(user: GetMinuteDto)

    @Delete
    fun deleteMinute(user: GetMinuteDto)

    @Query("DELETE FROM minute")
    fun deleteAllMinute()

    /**  MINUTE type CRUD */

}