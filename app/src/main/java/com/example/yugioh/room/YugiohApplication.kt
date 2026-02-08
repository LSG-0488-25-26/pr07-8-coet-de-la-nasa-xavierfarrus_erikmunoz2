package com.example.yugioh.room

import android.app.Application
import androidx.room.Room

class YugiohApplication : Application() {
    
    companion object {
        lateinit var database: YugiohDatabase
            private set
    }
    
    override fun onCreate() {
        super.onCreate()
        // Crear la instancia de la base de datos
        database = Room.databaseBuilder(
            this,
            YugiohDatabase::class.java,
            "yugioh_database"
        )
            .fallbackToDestructiveMigration() // En caso de cambios en la BD, se recrea
            .build()
    }
}
