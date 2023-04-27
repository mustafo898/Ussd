package com.composer.ussdaplication.data.local.dao

import androidx.room.*
import com.composer.ussdaplication.data.local.models.sms.GetSmsDb
import com.composer.ussdaplication.data.local.models.sms.GetSmsTypeDb
import com.composer.ussdaplication.data.remote.dto.sms.GetSmsDto

@Dao
interface SmsDao {

    /**  SMS type CRUD */

    @Query("SELECT * FROM smsTypeDto WHERE company = :company")
    fun getSmsType(company: String): List<GetSmsTypeDb>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun setSmsType(users: List<GetSmsTypeDb>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun setSmsType(user: GetSmsTypeDb)

    @Delete
    fun deleteSmsType(user: GetSmsTypeDb)

    @Query("DELETE FROM smsTypeDto")
    fun deleteAllSmsType()

    /**  SMS type CRUD */

    /**  SMS CRUD */

    @Query("SELECT * FROM sms WHERE typeId = :id AND company = :company")
    fun getSmsByIDList(id: String, company: String): List<GetSmsDb>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun setSms(users: List<GetSmsDb>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun setSms(user: GetSmsDb)

    @Delete
    fun deleteSms(user: GetSmsDb)

    @Query("DELETE FROM sms")
    fun deleteAllSms()

    /**  SMS type CRUD */
}