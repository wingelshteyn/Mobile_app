package ru.vladik.mobikiui.ui.screens

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.core.animateIntAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Air
import androidx.compose.material.icons.rounded.Refresh
import androidx.compose.material.icons.rounded.Thermostat
import androidx.compose.material.icons.rounded.WaterDrop
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import ru.vladik.mobikiui.data.WeatherCard
import ru.vladik.mobikiui.ui.components.GlassCard
import ru.vladik.mobikiui.ui.theme.AstrollColors
import ru.vladik.mobikiui.ui.weather.AnimatedWeatherIcon
import ru.vladik.mobikiui.ui.weather.WeatherUiState
import ru.vladik.mobikiui.ui.weather.WeatherViewModel
import ru.vladik.mobikiui.ui.weather.weatherVisualForCode

@Composable
fun WeatherScreen(
  viewModel: WeatherViewModel = viewModel()
) {
  val state by viewModel.state.collectAsState()

  Column(
    modifier = Modifier
      .fillMaxSize()
      .verticalScroll(rememberScrollState())
      .padding(horizontal = 16.dp, vertical = 12.dp),
    horizontalAlignment = Alignment.CenterHorizontally
  ) {
    AnimatedContent(
      targetState = state,
      transitionSpec = {
        (fadeIn(tween(350)) + slideInVertically { it / 4 }) togetherWith
          (fadeOut(tween(250)) + slideOutVertically { -it / 4 })
      },
      label = "weather_state"
    ) { s ->
      when (s) {
        is WeatherUiState.Loading -> WeatherLoadingCard()
        is WeatherUiState.Error -> WeatherErrorCard(s.message) { viewModel.refresh() }
        is WeatherUiState.Success -> WeatherSuccessCard(
          weather = s.weather,
          onRefresh = { viewModel.refresh() }
        )
      }
    }
  }
}

@Composable
private fun WeatherLoadingCard() {
  GlassCard(modifier = Modifier.fillMaxWidth()) {
    Column(
      modifier = Modifier.fillMaxWidth(),
      horizontalAlignment = Alignment.CenterHorizontally
    ) {
      CircularProgressIndicator(
        color = Color(0xFF4FC3F7),
        modifier = Modifier.size(40.dp),
        strokeWidth = 3.dp
      )
      Text(
        text = "Загружаем погоду…",
        color = AstrollColors.textPrimary,
        fontWeight = FontWeight.SemiBold,
        fontSize = 15.sp,
        modifier = Modifier.padding(top = 16.dp)
      )
      Text(
        text = "Open-Meteo API",
        color = AstrollColors.textDimmed,
        fontSize = 12.sp,
        modifier = Modifier.padding(top = 6.dp)
      )
    }
  }
}

@Composable
private fun WeatherErrorCard(message: String, onRetry: () -> Unit) {
  GlassCard(modifier = Modifier.fillMaxWidth()) {
    Text(
      text = "Не удалось загрузить",
      color = AstrollColors.textPrimary,
      fontWeight = FontWeight.Bold,
      fontSize = 16.sp
    )
    Text(
      text = message,
      color = AstrollColors.textDimmed,
      fontSize = 13.sp,
      modifier = Modifier.padding(vertical = 10.dp)
    )
    Button(onClick = onRetry) {
      Icon(Icons.Rounded.Refresh, contentDescription = null, modifier = Modifier.size(18.dp))
      Text("Повторить", modifier = Modifier.padding(start = 6.dp))
    }
  }
}

@Composable
private fun WeatherSuccessCard(weather: WeatherCard, onRefresh: () -> Unit) {
  val style = weatherVisualForCode(weather.weatherCode)
  val animatedTemp by animateIntAsState(
    targetValue = weather.tempC,
    animationSpec = tween(900),
    label = "temp"
  )

  GlassCard(modifier = Modifier.fillMaxWidth()) {
    Box(
      modifier = Modifier
        .fillMaxWidth()
        .height(140.dp)
        .clip(RoundedCornerShape(14.dp))
        .background(
          brush = Brush.radialGradient(
            colors = listOf(style.glowColor, Color.Transparent),
            radius = 280f
          )
        ),
      contentAlignment = Alignment.Center
    ) {
      AnimatedWeatherIcon(
        weatherCode = weather.weatherCode,
        size = 88.dp
      )
    }

    Text(
      text = weather.city,
      color = AstrollColors.textPrimary,
      fontWeight = FontWeight.ExtraBold,
      fontSize = 20.sp,
      style = MaterialTheme.typography.titleLarge,
      modifier = Modifier.padding(top = 8.dp)
    )
    Text(
      text = weather.condition,
      color = AstrollColors.textDimmed,
      fontSize = 14.sp
    )

    Row(
      modifier = Modifier
        .fillMaxWidth()
        .padding(top = 16.dp),
      verticalAlignment = Alignment.Bottom
    ) {
      Text(
        text = "$animatedTemp°",
        color = AstrollColors.accentSelected,
        fontSize = 52.sp,
        fontWeight = FontWeight.Bold
      )
      Text(
        text = "ощущается ${weather.feelsLikeC}°",
        color = AstrollColors.textDimmed,
        fontSize = 14.sp,
        modifier = Modifier.padding(start = 12.dp, bottom = 10.dp)
      )
    }

    Spacer(Modifier.height(12.dp))

    Row(
      modifier = Modifier.fillMaxWidth(),
      horizontalArrangement = Arrangement.spacedBy(10.dp)
    ) {
      WeatherStatChip(
        icon = Icons.Rounded.Air,
        label = "Ветер",
        value = weather.wind,
        modifier = Modifier.weight(1f)
      )
      WeatherStatChip(
        icon = Icons.Rounded.WaterDrop,
        label = "Влажность",
        value = weather.humidity,
        modifier = Modifier.weight(1f)
      )
    }

    WeatherStatChip(
      icon = Icons.Rounded.Thermostat,
      label = "Источник",
      value = "open-meteo.com",
      modifier = Modifier
        .fillMaxWidth()
        .padding(top = 10.dp)
    )

    Button(
      onClick = onRefresh,
      modifier = Modifier
        .fillMaxWidth()
        .padding(top = 14.dp),
      colors = ButtonDefaults.buttonColors(
        containerColor = Color(0xFF1E3A5F),
        contentColor = Color(0xFF90CAF9)
      )
    ) {
      Icon(Icons.Rounded.Refresh, contentDescription = null, modifier = Modifier.size(18.dp))
      Text("Обновить", modifier = Modifier.padding(start = 8.dp))
    }
  }
}

@Composable
private fun WeatherStatChip(
  icon: androidx.compose.ui.graphics.vector.ImageVector,
  label: String,
  value: String,
  modifier: Modifier = Modifier
) {
  Row(
    modifier = modifier
      .clip(RoundedCornerShape(12.dp))
      .background(Color.White.copy(alpha = 0.05f))
      .padding(horizontal = 12.dp, vertical = 10.dp),
    verticalAlignment = Alignment.CenterVertically
  ) {
    Icon(icon, contentDescription = null, tint = Color(0xFF64B5F6), modifier = Modifier.size(20.dp))
    Column(modifier = Modifier.padding(start = 10.dp)) {
      Text(text = label, color = AstrollColors.textDimmed, fontSize = 11.sp)
      Text(text = value, color = AstrollColors.textPrimary, fontSize = 13.sp, fontWeight = FontWeight.Medium)
    }
  }
}
