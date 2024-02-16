package com.company.masterdetail.data.di

import android.content.Context
import androidx.room.Room
import com.company.masterdetail.data.localstorage.AppDatabase
import com.company.masterdetail.domain.dao.PokeDetailLocalDao
import com.company.masterdetail.domain.dao.PokeDetailTypeLocalDao
import com.company.masterdetail.domain.dao.PokeLocalDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoomModule {
    @Singleton
    @Provides
    fun roomDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java, "masterdetaildatabse"
        ).build()
    }

    @Singleton
    @Provides
    fun pokeLocalDao(roomDatabase : AppDatabase): PokeLocalDao {
        return roomDatabase.pokedao()
    }

    @Singleton
    @Provides
    fun pokeDetailLocalDao(roomDatabase : AppDatabase): PokeDetailLocalDao {
        return roomDatabase.pokeDetaildao()
    }

    @Singleton
    @Provides
    fun pokeDetailTypeLocalDao(roomDatabase : AppDatabase): PokeDetailTypeLocalDao {
        return roomDatabase.pokeDetailTypedao()
    }
}

