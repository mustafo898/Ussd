package com.example.ussdaplication.data.local.dao

import androidx.room.*
import com.example.ussdaplication.data.remote.dto.minute.GetMinuteDto
import com.example.ussdaplication.data.remote.dto.sms.GetSmsDto
import com.example.ussdaplication.data.remote.dto.sms.GetSmsTypeDto

@Dao
interface SmsDao {

    /**  SMS type CRUD */

    @Query("SELECT * FROM smsTypeDto")
    fun getSmsType(): List<GetSmsTypeDto>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun setSmsType(users: List<GetSmsTypeDto>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun setSmsType(user: GetSmsTypeDto)

    @Delete
    fun deleteSmsType(user: GetSmsTypeDto)

    @Query("DELETE FROM smsTypeDto")
    fun deleteAllSmsType()

    /**  SMS type CRUD */

    /**  SMS CRUD */

    @Query("SELECT * FROM sms")
    fun getSms(): List<GetSmsDto>

    @Query("SELECT * FROM sms WHERE typeId = :id AND company = :company")
    fun getSmsByIDList(id: String, company: String): List<GetSmsDto>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun setSms(users: List<GetSmsDto>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun setSms(user: GetSmsDto)

    @Delete
    fun deleteSms(user: GetSmsDto)

    @Query("DELETE FROM sms")
    fun deleteAllSms()

    /**  SMS type CRUD */
}