package com.composer.ussdaplication.data.local.dao

import androidx.room.*
import com.composer.ussdaplication.data.local.models.internet.GetInternetDb
import com.composer.ussdaplication.data.local.models.internet.GetInternetTypeDb
import com.composer.ussdaplication.data.remote.dto.internet.GetInternetDto
import com.composer.ussdaplication.data.remote.dto.internet.GetInternetTypeDto

@Dao
interface InternetDao {

    /**  INTERNET type CRUD */

    @Query("SELECT * FROM internetTypeDb WHERE company = :company")
    fun getInternetType(company: String): List<GetInternetTypeDb>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun setInternetType(users: List<GetInternetTypeDb>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun setInternetType(user: GetInternetTypeDb)

    @Delete
    fun deleteInternetType(user: GetInternetTypeDb)

    @Query("DELETE FROM internetTypeDb")
    fun deleteAllInternetType()

    /**  INTERNET type CRUD */

    /**  INTERNET CRUD */

    @Query("SELECT * FROM internet WHERE typeId = :id AND company = :company")
    fun getInternetIDList(id: String, company: String): List<GetInternetDb>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun setInternet(users: List<GetInternetDb>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun setInternet(user: GetInternetDb)

    @Delete
    fun deleteInternet(user: GetInternetDb)

    @Query("DELETE FROM internet")
    fun deleteAllInternet()

    /**  INTERNET type CRUD */
}