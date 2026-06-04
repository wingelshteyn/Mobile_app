package ru.vladik.mobikiui.ui.components

import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.compose.LocalLifecycleOwner
import org.osmdroid.util.GeoPoint
import org.osmdroid.views.MapView
import org.osmdroid.views.overlay.Marker
import ru.vladik.mobikiui.data.MapMarker
import org.osmdroid.tileprovider.tilesource.TileSourceFactory

@Composable
fun OsmMapView(
  markers: List<MapMarker>,
  modifier: Modifier = Modifier,
  initialZoom: Double = 13.0
) {
  val context = LocalContext.current
  val lifecycleOwner = LocalLifecycleOwner.current

  val mapView = remember {
    MapView(context).apply {
      setTileSource(TileSourceFactory.MAPNIK)
      setMultiTouchControls(true)
      minZoomLevel = 5.0
      maxZoomLevel = 19.0
    }
  }

  DisposableEffect(lifecycleOwner, mapView) {
    val observer = LifecycleEventObserver { _, event ->
      when (event) {
        Lifecycle.Event.ON_RESUME -> mapView.onResume()
        Lifecycle.Event.ON_PAUSE -> mapView.onPause()
        else -> Unit
      }
    }
    lifecycleOwner.lifecycle.addObserver(observer)
    onDispose {
      lifecycleOwner.lifecycle.removeObserver(observer)
      mapView.onDetach()
    }
  }

  AndroidView(
    factory = { mapView },
    modifier = modifier,
    update = { map ->
      map.overlays.clear()

      markers.forEach { item ->
        val marker = Marker(map).apply {
          position = GeoPoint(item.latitude, item.longitude)
          title = item.title
          snippet = item.description
          setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM)
        }
        map.overlays.add(marker)
      }

      if (markers.isNotEmpty()) {
        val center = GeoPoint(markers.first().latitude, markers.first().longitude)
        map.controller.setZoom(initialZoom)
        map.controller.setCenter(center)
      }
    }
  )
}
