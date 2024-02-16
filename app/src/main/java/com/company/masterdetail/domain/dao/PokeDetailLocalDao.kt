package com.company.masterdetail.domain.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.company.masterdetail.domain.entities.DetailWithTypes
import com.company.masterdetail.domain.entities.PokeDetailEntity

@Dao
interface PokeDetailLocalDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertAll(vararg pokeDetailEntity: PokeDetailEntity)

    @Transaction
    @Query("SELECT * FROM PokeDetailEntity where id = :id")
    fun getDetail(id: Long?): DetailWithTypes
}