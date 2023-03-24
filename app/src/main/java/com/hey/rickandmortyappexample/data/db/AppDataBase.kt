package com.hey.rickandmortyappexample.data.db

import android.app.Application
import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.hey.rickandmortyappexample.data.db.converters.Converters
import com.hey.rickandmortyappexample.data.db.dao.CharacterDao
import com.hey.rickandmortyappexample.data.db.entity.character.CharacterEntity
import com.hey.rickandmortyappexample.data.db.entity.character.LocationMinEntity
import com.hey.rickandmortyappexample.data.db.entity.character.OriginEntity
import kotlinx.coroutines.CoroutineScope

@Database(entities = [CharacterEntity::class, LocationMinEntity::class, OriginEntity::class],
    version = 1,
    exportSchema = false)
@TypeConverters(Converters::class)
abstract class AppDataBase : RoomDatabase() {

    abstract fun characterDao() : CharacterDao

    companion object {
        @Volatile
        private var INSTANCE: AppDataBase? = null

        fun getDatabase(context: Context, scope: CoroutineScope): AppDataBase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDataBase::class.java,
                    "rick_morty_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }

}