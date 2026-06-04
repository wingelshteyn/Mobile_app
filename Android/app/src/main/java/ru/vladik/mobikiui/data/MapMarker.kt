package ru.vladik.mobikiui.data

data class MapMarker(
  val title: String,
  val description: String,
  val latitude: Double,
  val longitude: Double
)

object MapMarkers {
  /** Томск — точки для демонстрации меток на OSM */
  val tomsk = listOf(
    MapMarker(
      title = "Площадь Ленина",
      description = "Центр города, остановки общественного транспорта",
      latitude = 56.4884,
      longitude = 84.9480
    ),
    MapMarker(
      title = "Томский государственный университет",
      description = "Главный корпус на пр. Ленина",
      latitude = 56.4743,
      longitude = 84.9488
    ),
    MapMarker(
      title = "Набережная р. Томи",
      description = "Прогулочная зона у реки",
      latitude = 56.4912,
      longitude = 84.9455
    ),
    MapMarker(
      title = "Ледовый дворец",
      description = "Спортивный комплекс",
      latitude = 56.4658,
      longitude = 84.9602
    )
  )
}
