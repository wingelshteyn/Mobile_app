package ru.vladik.mobikiui.ui.notes

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import ru.vladik.mobikiui.data.NotesRepository
import ru.vladik.mobikiui.db.NoteEntity

class NotesViewModel(private val repository: NotesRepository) : ViewModel() {

  val notes = repository.observeNotes().stateIn(
    scope = viewModelScope,
    started = SharingStarted.WhileSubscribed(5_000),
    initialValue = emptyList()
  )

  fun createNote(title: String, body: String, tag: String) {
    viewModelScope.launch {
      if (title.isBlank()) return@launch
      repository.createNote(title, body, tag.ifBlank { "без тега" })
    }
  }

  fun updateNote(note: NoteEntity, title: String, body: String, tag: String) {
    viewModelScope.launch {
      if (title.isBlank()) return@launch
      repository.updateNote(
        note.copy(
          title = title.trim(),
          body = body.trim(),
          tag = tag.trim().ifBlank { "без тега" }
        )
      )
    }
  }

  fun deleteNote(note: NoteEntity) {
    viewModelScope.launch {
      repository.deleteNote(note)
    }
  }
}

class NotesViewModelFactory(
  private val repository: NotesRepository
) : ViewModelProvider.Factory {
  @Suppress("UNCHECKED_CAST")
  override fun <T : ViewModel> create(modelClass: Class<T>): T {
    if (modelClass.isAssignableFrom(NotesViewModel::class.java)) {
      return NotesViewModel(repository) as T
    }
    throw IllegalArgumentException("Unknown ViewModel: ${modelClass.name}")
  }
}
