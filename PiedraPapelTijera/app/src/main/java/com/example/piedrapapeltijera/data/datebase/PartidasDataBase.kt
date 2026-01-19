package com.example.piedrapapeltijera.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.piedrapapeltijera.domain.entities.Player
import com.example.piedrapapeltijera.domain.interfaces.DAO.PlayerDao

@Database(entities = [Player::class], version = 1, exportSchema = false)
abstract class PartidasDataBase : RoomDatabase() {

    abstract fun playerDao(): PlayerDao

    companion object {
        @Volatile
        private var INSTANCE: PartidasDataBase? = null

        fun getDatabase(context: Context): PartidasDataBase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    PartidasDataBase::class.java,
                    "partidas_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}