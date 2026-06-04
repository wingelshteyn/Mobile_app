package ru.vladik.mobikiui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import ru.vladik.mobikiui.ui.AppRoot
import ru.vladik.mobikiui.ui.theme.MobikiTheme

class MainActivity : ComponentActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    enableEdgeToEdge()
    setContent {
      MobikiTheme {
        Surface(modifier = Modifier.fillMaxSize(), color = Color.Transparent) {
          AppRoot()
        }
      }
    }
  }
}
