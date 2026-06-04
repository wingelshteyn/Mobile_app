package ru.vladik.mobikiui.data

import kotlinx.coroutines.flow.Flow
import ru.vladik.mobikiui.db.NoteDao
import ru.vladik.mobikiui.db.NoteEntity

/**
 * CRUD для заметок (практическая 3).
 */
class NotesRepository(private val dao: NoteDao) {

  fun observeNotes(): Flow<List<NoteEntity>> = dao.observeAll()

  suspend fun createNote(title: String, body: String, tag: String): Long {
    return dao.insert(NoteEntity(title = title.trim(), body = body.trim(), tag = tag.trim()))
  }

  suspend fun readNote(id: Long): NoteEntity? = dao.getById(id)

  suspend fun updateNote(note: NoteEntity) {
    dao.update(note)
  }

  suspend fun deleteNote(note: NoteEntity) {
    dao.delete(note)
  }
}
