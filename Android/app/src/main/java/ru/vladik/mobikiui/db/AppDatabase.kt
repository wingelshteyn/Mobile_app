package ru.vladik.mobikiui.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [NoteEntity::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

  abstract fun noteDao(): NoteDao

  companion object {
    fun create(context: Context): AppDatabase {
      return Room.databaseBuilder(
        context.applicationContext,
        AppDatabase::class.java,
        "mobiki.db"
      ).build()
    }
  }
}
