package ru.vladik.mobikiui.data.weather

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.vladik.mobikiui.data.WeatherCard
import kotlin.math.roundToInt

object WeatherRepository {

  private const val BASE_URL = "https://api.open-meteo.com/"
  private const val TOMSK_LAT = 56.4846
  private const val TOMSK_LON = 84.9482

  private val api: OpenMeteoApi by lazy {
    Retrofit.Builder()
      .baseUrl(BASE_URL)
      .addConverterFactory(GsonConverterFactory.create())
      .build()
      .create(OpenMeteoApi::class.java)
  }

  suspend fun loadTomskWeather(): WeatherCard = withContext(Dispatchers.IO) {
    val response = api.forecast(latitude = TOMSK_LAT, longitude = TOMSK_LON)
    val current = response.current
      ?: throw IllegalStateException("Сервер не вернул блок current")

    WeatherCard(
      city = "Томск (Open-Meteo)",
      condition = wmoDescription(current.weather_code),
      tempC = current.temperature_2m?.roundToInt() ?: 0,
      feelsLikeC = current.apparent_temperature?.roundToInt() ?: 0,
      wind = "${current.wind_speed_10m?.let { "%.1f".format(it) } ?: "—"} м/с",
      humidity = "${current.relative_humidity_2m ?: "—"}%"
    )
  }

  private fun wmoDescription(code: Int?): String = when (code) {
    0 -> "Ясно"
    1, 2, 3 -> "Переменная облачность"
    45, 48 -> "Туман"
    51, 53, 55 -> "Морось"
    61, 63, 65 -> "Дождь"
    71, 73, 75 -> "Снег"
    80, 81, 82 -> "Ливень"
    95, 96, 99 -> "Гроза"
    null -> "Нет данных"
    else -> "Погода (код $code)"
  }
}
