package ru.vladik.mobikiui.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import ru.vladik.mobikiui.MobikiApplication
import ru.vladik.mobikiui.data.NotesRepository
import ru.vladik.mobikiui.ui.components.GlassCard
import ru.vladik.mobikiui.ui.notes.NotesViewModel
import ru.vladik.mobikiui.ui.notes.NotesViewModelFactory
import ru.vladik.mobikiui.ui.theme.AstrollColors

@Composable
private fun noteFieldColors() = OutlinedTextFieldDefaults.colors(
  focusedTextColor = AstrollColors.textBody,
  unfocusedTextColor = AstrollColors.textBody,
  focusedBorderColor = Color.White.copy(alpha = 0.22f),
  unfocusedBorderColor = Color.White.copy(alpha = 0.10f),
  focusedLabelColor = AstrollColors.accentSoft,
  unfocusedLabelColor = AstrollColors.textDimmed,
  cursorColor = AstrollColors.accentSelected
)

@Composable
fun NotesScreen() {
  val context = LocalContext.current
  val repository = remember(context.applicationContext) {
    NotesRepository((context.applicationContext as MobikiApplication).database.noteDao())
  }
  val vm: NotesViewModel = viewModel(factory = NotesViewModelFactory(repository))
  val notes by vm.notes.collectAsStateWithLifecycle()

  var title by remember { mutableStateOf("") }
  var body by remember { mutableStateOf("") }
  var tag by remember { mutableStateOf("") }
  var editingId by remember { mutableStateOf<Long?>(null) }

  fun clearForm() {
    title = ""
    body = ""
    tag = ""
    editingId = null
  }

  Column(modifier = Modifier.fillMaxSize()) {
    LazyColumn(
      modifier = Modifier.fillMaxSize(),
      contentPadding = PaddingValues(16.dp),
      verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
      item {
        GlassCard {
          Text(
            text = if (editingId == null) "Новая заметка" else "Редактирование",
            color = AstrollColors.textPrimary,
            fontWeight = FontWeight.ExtraBold,
            fontSize = 14.sp
          )
          Spacer(Modifier.height(10.dp))
          OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = title,
            onValueChange = { title = it },
            label = { Text("Заголовок") },
            singleLine = true,
            colors = noteFieldColors()
          )
          Spacer(Modifier.height(8.dp))
          OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = body,
            onValueChange = { body = it },
            label = { Text("Текст") },
            minLines = 3,
            colors = noteFieldColors()
          )
          Spacer(Modifier.height(8.dp))
          OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = tag,
            onValueChange = { tag = it },
            label = { Text("Тег") },
            singleLine = true,
            colors = noteFieldColors()
          )
          Spacer(Modifier.height(12.dp))
          Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalAlignment = Alignment.CenterVertically
          ) {
            if (editingId != null) {
              Button(
                onClick = {
                  val id = editingId ?: return@Button
                  val existing = notes.firstOrNull { it.id == id } ?: return@Button
                  vm.updateNote(existing, title, body, tag)
                  clearForm()
                }
              ) {
                Text("Обновить")
              }
              TextButton(onClick = { clearForm() }) {
                Text("Отмена")
              }
            } else {
              Button(
                onClick = {
                  vm.createNote(title, body, tag)
                  clearForm()
                }
              ) {
                Text("Сохранить заметку")
              }
            }
          }
        }
      }

      items(notes, key = { it.id }) { note ->
        GlassCard {
          Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
          ) {
            Column(modifier = Modifier.weight(1f)) {
              Text(
                text = note.title,
                color = AstrollColors.textPrimary,
                fontWeight = FontWeight.ExtraBold,
                fontSize = 14.sp,
                style = MaterialTheme.typography.titleMedium
              )
              Text(
                text = note.body,
                color = AstrollColors.textDimmed,
                fontSize = 12.sp
              )
              Text(
                modifier = Modifier.padding(top = 6.dp),
                text = "#${note.tag}",
                color = AstrollColors.accentSoft,
                fontSize = 11.sp,
                fontWeight = FontWeight.Bold
              )
            }
            IconButton(
              onClick = {
                editingId = note.id
                title = note.title
                body = note.body
                tag = note.tag
              }
            ) {
              Icon(Icons.Outlined.Edit, contentDescription = "Редактировать", tint = AstrollColors.accentSoft)
            }
            IconButton(onClick = { vm.deleteNote(note) }) {
              Icon(Icons.Outlined.Delete, contentDescription = "Удалить", tint = Color(0xEBF5D6D8))
            }
          }
        }
      }
    }
  }
}
