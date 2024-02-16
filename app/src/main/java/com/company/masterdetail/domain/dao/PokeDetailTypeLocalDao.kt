package com.company.masterdetail.domain.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.company.masterdetail.domain.entities.DetailTypeEntity

@Dao
interface PokeDetailTypeLocalDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertAll(vararg : DetailTypeEntity)
}