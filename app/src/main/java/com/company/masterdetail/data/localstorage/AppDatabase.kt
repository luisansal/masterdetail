package com.company.masterdetail.data.localstorage

import androidx.room.Database
import androidx.room.RoomDatabase
import com.company.masterdetail.domain.dao.PokeDetailLocalDao
import com.company.masterdetail.domain.dao.PokeDetailTypeLocalDao
import com.company.masterdetail.domain.dao.PokeLocalDao
import com.company.masterdetail.domain.entities.PokeDetailEntity
import com.company.masterdetail.domain.entities.PokeEntity
import com.company.masterdetail.domain.entities.DetailTypeEntity

@Database(entities = [PokeEntity::class, PokeDetailEntity::class, DetailTypeEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun pokedao(): PokeLocalDao
    abstract fun pokeDetaildao(): PokeDetailLocalDao
    abstract fun pokeDetailTypedao(): PokeDetailTypeLocalDao
}