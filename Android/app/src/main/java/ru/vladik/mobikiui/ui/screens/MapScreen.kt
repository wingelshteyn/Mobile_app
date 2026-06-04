package ru.vladik.mobikiui.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.vladik.mobikiui.data.MapMarkers
import ru.vladik.mobikiui.ui.components.GlassCard
import ru.vladik.mobikiui.ui.components.LeafletMapView
import ru.vladik.mobikiui.ui.theme.AstrollColors

@Composable
fun MapScreen() {
  val markers = MapMarkers.tomsk
  Column(modifier = Modifier.fillMaxSize()) {
    GlassCard(
      modifier = Modifier
        .fillMaxWidth()
        .padding(horizontal = 16.dp, vertical = 12.dp)
    ) {
      Text(
        text = "OpenStreetMap",
        color = AstrollColors.textPrimary,
        fontWeight = FontWeight.ExtraBold,
        fontSize = 14.sp,
        style = MaterialTheme.typography.titleMedium
      )
      Text(
        text = "Тайлы OSM • метки с описанием • масштаб двумя пальцами",
        color = AstrollColors.textDimmed,
        fontSize = 12.sp,
        modifier = Modifier.padding(bottom = 10.dp)
      )
      LeafletMapView(
        markers = markers,
        modifier = Modifier
          .fillMaxWidth()
          .height(300.dp)
      )
    }

    Column(
      modifier = Modifier
        .weight(1f)
        .verticalScroll(rememberScrollState())
    ) {
      GlassCard(modifier = Modifier.padding(horizontal = 16.dp, vertical = 4.dp)) {
        Text(
          text = "Метки (${markers.size})",
          color = AstrollColors.textPrimary,
          fontWeight = FontWeight.Bold,
          fontSize = 13.sp,
          modifier = Modifier.padding(bottom = 8.dp)
        )
        markers.forEach { m ->
          Text(
            text = m.title,
            color = AstrollColors.accentSelected,
            fontWeight = FontWeight.SemiBold,
            fontSize = 13.sp
          )
          Text(
            text = m.description,
            color = AstrollColors.textDimmed,
            fontSize = 12.sp,
            modifier = Modifier.padding(bottom = 10.dp)
          )
        }
      }
    }
  }
}
