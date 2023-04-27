package com.composer.ussdaplication.data.local.dao

import androidx.room.*
import com.composer.ussdaplication.data.local.models.ussd.GetUssdDtoDb

@Dao
interface UssdDao {

    /**  USSD CRUD */

    @Query("SELECT * FROM ussd where company = :company")
    fun getUssd(company: String): List<GetUssdDtoDb>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun setUssd(users: List<GetUssdDtoDb>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun setUssd(user: GetUssdDtoDb)

    @Delete
    fun deleteUssd(user: GetUssdDtoDb)

    @Query("DELETE FROM ussd")
    fun deleteAllUssd()

    /**  USSD CRUD */

}