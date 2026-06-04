package ru.vladik.mobikiui.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import kotlinx.coroutines.launch
import ru.vladik.mobikiui.ui.components.AstrollBackground
import ru.vladik.mobikiui.ui.components.TelegramAppBar
import ru.vladik.mobikiui.ui.components.TelegramDrawerContent
import ru.vladik.mobikiui.ui.screens.ChatsScreen
import ru.vladik.mobikiui.ui.screens.MapScreen
import ru.vladik.mobikiui.ui.screens.NotesScreen
import ru.vladik.mobikiui.ui.screens.SettingsScreen
import ru.vladik.mobikiui.ui.screens.WeatherScreen
import ru.vladik.mobikiui.ui.theme.AstrollColors

private fun titleFor(tab: AppTab): String = when (tab) {
  AppTab.Chats -> "Чаты"
  AppTab.Notes -> "Заметки"
  AppTab.Weather -> "Погода"
  AppTab.Map -> "Карта"
  AppTab.Settings -> "Настройки"
}

@Composable
fun AppRoot() {
  var activeTab by remember { mutableStateOf(AppTab.Chats) }
  val drawerState = rememberDrawerState(DrawerValue.Closed)
  val scope = rememberCoroutineScope()

  Box(modifier = Modifier.fillMaxSize()) {
    AstrollBackground(Modifier.fillMaxSize())
    ModalNavigationDrawer(
      drawerState = drawerState,
      gesturesEnabled = true,
      drawerContent = {
        ModalDrawerSheet(
          drawerContainerColor = Color.Transparent,
          drawerContentColor = AstrollColors.textBody
        ) {
          TelegramDrawerContent(
            selected = activeTab,
            onSelect = { tab ->
              activeTab = tab
              scope.launch { drawerState.close() }
            }
          )
        }
      }
    ) {
      Scaffold(
        modifier = Modifier.fillMaxSize(),
        containerColor = Color.Transparent,
        contentColor = AstrollColors.textBody,
        topBar = {
          Column(Modifier.statusBarsPadding()) {
            TelegramAppBar(
              title = titleFor(activeTab),
              onMenuClick = { scope.launch { drawerState.open() } }
            )
          }
        }
      ) { padding ->
        AppContent(activeTab = activeTab, padding = padding)
      }
    }
  }
}

@Composable
private fun AppContent(activeTab: AppTab, padding: PaddingValues) {
  Box(
    modifier = Modifier
      .fillMaxSize()
      .padding(padding)
  ) {
    when (activeTab) {
      AppTab.Chats -> ChatsScreen()
      AppTab.Notes -> NotesScreen()
      AppTab.Weather -> WeatherScreen()
      AppTab.Map -> MapScreen()
      AppTab.Settings -> SettingsScreen()
    }
  }
}
