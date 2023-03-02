package com.example.ussdaplication.data.local.dao

import androidx.room.*
import com.example.ussdaplication.data.remote.dto.ussd.GetUssdDto

@Dao
interface UssdDao {

    /**  USSD CRUD */

    @Query("SELECT * FROM ussd")
    fun getUssd(): List<GetUssdDto>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun setUssd(users: List<GetUssdDto>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun setUssd(user: GetUssdDto)

    @Delete
    fun deleteUssd(user: GetUssdDto)

    @Query("DELETE FROM ussd")
    fun deleteAllUssd()

    /**  USSD CRUD */

}