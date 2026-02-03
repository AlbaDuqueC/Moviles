package com.example.hostaldigital.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.hostaldigital.domain.dao.BookingDao
import com.example.hostaldigital.domain.dao.RoomDao
import com.example.hostaldigital.domain.dao.UserDao
import com.example.hostaldigital.domain.entities.RoomEntity
import com.example.hostaldigital.domain.entities.UserEntity
import com.example.hostaldigital.domain.entities.BookingEntity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Database(
    entities = [UserEntity::class, RoomEntity::class, BookingEntity::class],
    version = 1,
    exportSchema = false
)
abstract class HostalDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun roomDao(): RoomDao
    abstract fun bookingDao(): BookingDao

    companion object {
        @Volatile
        private var INSTANCE: HostalDatabase? = null

        fun getDatabase(context: Context): HostalDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    HostalDatabase::class.java,
                    "hostal_database"
                )
                    .addCallback(DatabaseCallback())
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }

    private class DatabaseCallback : Callback() {
        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)
            INSTANCE?.let { database ->
                CoroutineScope(Dispatchers.IO).launch {
                    populateDatabase(database)
                }
            }
        }

        suspend fun populateDatabase(database: HostalDatabase) {
            val userDao = database.userDao()
            val roomDao = database.roomDao()

            // Crear usuario dueño
            userDao.insert(
                UserEntity(
                    name = "alba",
                    password = "alba123",
                    email = "a@gmail.com",
                    isOwner = true,

                )
            )

            // Crear habitaciones iniciales
            val rooms = listOf(
                RoomEntity(
                    roomNumber = "101",
                    type = "Single",
                    price = 50.0,
                    capacity = 1,
                    description = "Habitación individual con cama sencilla, TV y baño privado.",
                    isAvailable = true
                ),
                RoomEntity(
                    roomNumber = "102",
                    type = "Double",
                    price = 75.0,
                    capacity = 2,
                    description = "Habitación doble con cama matrimonial, TV, minibar y baño privado.",
                    isAvailable = true
                ),
                RoomEntity(
                    roomNumber = "103",
                    type = "Double",
                    price = 75.0,
                    capacity = 2,
                    description = "Habitación doble con dos camas individuales, TV y baño privado.",
                    isAvailable = true
                ),
                RoomEntity(
                    roomNumber = "201",
                    type = "Suite",
                    price = 120.0,
                    capacity = 3,
                    description = "Suite de lujo con sala de estar, cama king size, jacuzzi y balcón.",
                    isAvailable = true
                ),
                RoomEntity(
                    roomNumber = "202",
                    type = "Suite",
                    price = 120.0,
                    capacity = 3,
                    description = "Suite familiar con dos habitaciones, sala de estar y cocina pequeña.",
                    isAvailable = true
                )
            )
            roomDao.insertAll(rooms)
        }
    }
}