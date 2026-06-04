package ru.vladik.mobikiui.data.weather

import retrofit2.http.GET
import retrofit2.http.Query

interface OpenMeteoApi {
  @GET("v1/forecast")
  suspend fun forecast(
    @Query("latitude") latitude: Double,
    @Query("longitude") longitude: Double,
    @Query("current") current: String =
      "temperature_2m,relative_humidity_2m,apparent_temperature,weather_code,wind_speed_10m",
    @Query("timezone") timezone: String = "Asia/Barnaul"
  ): OpenMeteoResponse
}

data class OpenMeteoResponse(
  val latitude: Double?,
  val longitude: Double?,
  val current: CurrentWeatherDto?
)

data class CurrentWeatherDto(
  val time: String?,
  val temperature_2m: Double?,
  val apparent_temperature: Double?,
  val relative_humidity_2m: Int?,
  val weather_code: Int?,
  val wind_speed_10m: Double?
)
