package ru.vladik.mobikiui.ui.components

import android.annotation.SuppressLint
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView
import ru.vladik.mobikiui.data.MapMarker

@SuppressLint("SetJavaScriptEnabled")
@Composable
fun LeafletMapView(
  markers: List<MapMarker>,
  modifier: Modifier = Modifier,
  initialZoom: Int = 13
) {
  val context = LocalContext.current
  val html = remember(markers, initialZoom) { buildLeafletHtml(markers, initialZoom) }

  AndroidView(
    modifier = modifier,
    factory = {
      WebView(context).apply {
        settings.javaScriptEnabled = true
        settings.domStorageEnabled = true
        settings.cacheMode = WebSettings.LOAD_DEFAULT
        settings.loadsImagesAutomatically = true
        webViewClient = WebViewClient()
        loadDataWithBaseURL(
          "https://app.local/",
          html,
          "text/html",
          "UTF-8",
          null
        )
      }
    },
    update = { webView ->
      webView.loadDataWithBaseURL(
        "https://app.local/",
        html,
        "text/html",
        "UTF-8",
        null
      )
    }
  )
}

private fun buildLeafletHtml(markers: List<MapMarker>, initialZoom: Int): String {
  val centerLat = markers.map { it.latitude }.average().takeIf { !it.isNaN() } ?: 56.4884
  val centerLon = markers.map { it.longitude }.average().takeIf { !it.isNaN() } ?: 84.9480
  val markersJson = markers.joinToString(",") { m ->
    """{"lat":${m.latitude},"lng":${m.longitude},"title":${m.title.jsonQuoted()},"desc":${m.description.jsonQuoted()}}"""
  }

  return """
    <!DOCTYPE html>
    <html>
    <head>
      <meta charset="utf-8"/>
      <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no"/>
      <link rel="stylesheet" href="https://unpkg.com/leaflet@1.9.4/dist/leaflet.css"/>
      <script src="https://unpkg.com/leaflet@1.9.4/dist/leaflet.js"></script>
      <style>
        html, body, #map { height: 100%; width: 100%; margin: 0; padding: 0; background: #1a1d24; }
        .leaflet-popup-content-wrapper { border-radius: 10px; }
      </style>
    </head>
    <body>
      <div id="map"></div>
      <script>
        var map = L.map('map', { zoomControl: true }).setView([$centerLat, $centerLon], $initialZoom);
        L.tileLayer('https://{s}.tile.openstreetmap.de/{z}/{x}/{y}.png', {
          subdomains: ['a','b','c'],
          maxZoom: 19,
          attribution: '&copy; <a href="https://www.openstreetmap.org/copyright">OSM</a>'
        }).addTo(map);
        var items = [$markersJson];
        var layers = [];
        items.forEach(function(m) {
          var marker = L.marker([m.lat, m.lng]).addTo(map)
            .bindPopup('<b>' + m.title + '</b><br>' + m.desc);
          layers.push(marker);
        });
        if (items.length > 1) {
          var group = L.featureGroup(layers);
          map.fitBounds(group.getBounds().pad(0.15));
        }
      </script>
    </body>
    </html>
  """.trimIndent()
}

private fun String.jsonQuoted(): String =
  "\"" + replace("\\", "\\\\").replace("\"", "\\\"").replace("\n", "\\n") + "\""
