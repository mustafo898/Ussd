package com.example.ussdaplication.data.local.dao

import androidx.room.*
import com.example.ussdaplication.data.remote.dto.internet.GetInternetDto
import com.example.ussdaplication.data.remote.dto.internet.GetInternetTypeDto

@Dao
interface InternetDao {

    /**  INTERNET type CRUD */

    @Query("SELECT * FROM internetTypeDto")
    fun getInternetType(): List<GetInternetTypeDto>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun setInternetType(users: List<GetInternetTypeDto>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun setInternetType(user: GetInternetTypeDto)

    @Delete
    fun deleteInternetType(user: GetInternetTypeDto)

    @Query("DELETE FROM internetTypeDto")
    fun deleteAllInternetType()

    /**  INTERNET type CRUD */

    /**  INTERNET CRUD */

    @Query("SELECT * FROM internet")
    fun getInternet(): List<GetInternetDto>

    @Query("SELECT * FROM internet WHERE typeId = :id AND company = :company")
    fun getInternetIDList(id: String, company: String): List<GetInternetDto>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun setInternet(users: List<GetInternetDto>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun setInternet(user: GetInternetDto)

    @Delete
    fun deleteInternet(user: GetInternetDto)

    @Query("DELETE FROM internet")
    fun deleteAllInternet()

    /**  INTERNET type CRUD */
}