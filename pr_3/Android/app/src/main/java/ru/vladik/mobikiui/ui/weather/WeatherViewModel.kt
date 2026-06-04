package ru.vladik.mobikiui.ui.weather

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import ru.vladik.mobikiui.data.WeatherCard
import ru.vladik.mobikiui.data.weather.WeatherRepository

sealed interface WeatherUiState {
  data object Loading : WeatherUiState
  data class Success(val weather: WeatherCard) : WeatherUiState
  data class Error(val message: String) : WeatherUiState
}

class WeatherViewModel : ViewModel() {

  private val _state = MutableStateFlow<WeatherUiState>(WeatherUiState.Loading)
  val state: StateFlow<WeatherUiState> = _state.asStateFlow()

  init {
    refresh()
  }

  fun refresh() {
    viewModelScope.launch {
      _state.value = WeatherUiState.Loading
      try {
        _state.value = WeatherUiState.Success(WeatherRepository.loadTomskWeather())
      } catch (e: Exception) {
        _state.value = WeatherUiState.Error(
          e.message ?: "Не удалось загрузить погоду. Проверьте интернет."
        )
      }
    }
  }
}
