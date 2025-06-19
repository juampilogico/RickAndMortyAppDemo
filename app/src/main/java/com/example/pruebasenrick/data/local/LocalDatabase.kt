package com.example.pruebasenrick.data.local



import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.pruebasenrick.data.local.Converters
import androidx.room.TypeConverters

@Database(entities = [PersonajesLocal::class], version = 1)
@TypeConverters(Converters::class)
abstract class LocalDatabase : RoomDatabase() {

    abstract fun personajesDao(): IPersonajesDao

    companion object {
        @Volatile
        private var instance: LocalDatabase? = null

        fun getInstance(context: Context): LocalDatabase =
            instance ?: synchronized(this) {
                instance ?: buildDatabase(context).also { instance = it }
            }

        private fun buildDatabase(context: Context): LocalDatabase =
            Room.databaseBuilder(
                context.applicationContext,
                LocalDatabase::class.java,
                "favoritos-db"
            ).build()
    }
}
