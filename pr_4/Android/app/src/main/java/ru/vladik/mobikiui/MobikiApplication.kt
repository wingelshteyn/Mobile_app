package ru.vladik.mobikiui

import android.app.Application
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import ru.vladik.mobikiui.data.StaticData
import ru.vladik.mobikiui.db.AppDatabase
import ru.vladik.mobikiui.db.NoteEntity

class MobikiApplication : Application() {

  val database: AppDatabase by lazy { AppDatabase.create(this) }

  private val appScope = CoroutineScope(SupervisorJob() + Dispatchers.IO)

  override fun onCreate() {
    super.onCreate()
    appScope.launch {
      val dao = database.noteDao()
      if (dao.count() == 0) {
        StaticData.notes.forEach { n ->
          dao.insert(
            NoteEntity(
              title = n.title,
              body = n.body,
              tag = n.tag
            )
          )
        }
      }
    }
  }
}
