package ru.vladik.mobikiui.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import ru.vladik.mobikiui.data.WeatherCard
import ru.vladik.mobikiui.ui.components.GlassCard
import ru.vladik.mobikiui.ui.theme.AstrollColors
import ru.vladik.mobikiui.ui.weather.WeatherUiState
import ru.vladik.mobikiui.ui.weather.WeatherViewModel

@Composable
fun WeatherScreen(
  viewModel: WeatherViewModel = viewModel()
) {
  val state by viewModel.state.collectAsState()

  Column(
    modifier = Modifier.fillMaxSize(),
    horizontalAlignment = Alignment.CenterHorizontally
  ) {
    when (val s = state) {
      is WeatherUiState.Loading -> {
        GlassCard(modifier = Modifier.padding(16.dp)) {
          CircularProgressIndicator(color = AstrollColors.accentSelected)
          Text(
            text = "Загрузка погоды…",
            color = AstrollColors.textDimmed,
            fontSize = 12.sp,
            modifier = Modifier.padding(top = 12.dp)
          )
          Text(
            text = "API: open-meteo.com (бесплатный, без ключа)",
            color = AstrollColors.textDimmed,
            fontSize = 11.sp,
            modifier = Modifier.padding(top = 6.dp)
          )
        }
      }

      is WeatherUiState.Error -> {
        GlassCard(modifier = Modifier.padding(16.dp)) {
          Text(
            text = "Ошибка",
            color = AstrollColors.textPrimary,
            fontWeight = FontWeight.Bold,
            fontSize = 14.sp
          )
          Text(
            text = s.message,
            color = AstrollColors.textDimmed,
            fontSize = 12.sp,
            modifier = Modifier.padding(vertical = 8.dp)
          )
          Button(onClick = { viewModel.refresh() }) {
            Text("Повторить")
          }
        }
      }

      is WeatherUiState.Success -> WeatherContent(
        weather = s.weather,
        onRefresh = { viewModel.refresh() }
      )
    }
  }
}

@Composable
private fun WeatherContent(weather: WeatherCard, onRefresh: () -> Unit) {
  GlassCard(modifier = Modifier.padding(16.dp)) {
    Text(
      text = weather.city,
      color = AstrollColors.textPrimary,
      fontWeight = FontWeight.ExtraBold,
      fontSize = 16.sp,
      style = MaterialTheme.typography.titleLarge
    )
    Text(text = weather.condition, color = AstrollColors.textDimmed, fontSize = 12.sp)
    Text(
      modifier = Modifier.padding(top = 12.dp),
      text = "${weather.tempC}°C  (ощущается как ${weather.feelsLikeC}°C)",
      color = AstrollColors.accentSelected,
      fontSize = 14.sp,
      fontWeight = FontWeight.Bold
    )
    Text(
      modifier = Modifier.padding(top = 8.dp),
      text = "Ветер: ${weather.wind} • Влажность: ${weather.humidity}",
      color = AstrollColors.textDimmed,
      fontSize = 12.sp
    )
    Text(
      modifier = Modifier.padding(top = 10.dp),
      text = "Данные загружаются из открытого API при открытии вкладки.",
      color = Color.White.copy(alpha = 0.45f),
      fontSize = 11.sp
    )
    Button(
      onClick = onRefresh,
      modifier = Modifier.padding(top = 12.dp)
    ) {
      Text("Обновить")
    }
  }
}
