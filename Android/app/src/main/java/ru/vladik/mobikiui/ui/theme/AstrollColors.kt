package ru.vladik.mobikiui.ui.theme

import androidx.compose.ui.graphics.Color

/**
 * Цвета и прозрачности в духе astroll-client
 * (`characterListShell.module.css` shell + `mainMenu.module.css` panel / glassTile).
 */
object AstrollColors {
  val shellBaseTop = Color(0xFF0B0C10)
  val shellBaseBottom = Color(0xFF07080B)
  val radial1 = Color(0xFF14161B)
  val radial2 = Color(0xFF101218)

  val textPrimary = Color(0xF5F8F9FC) // rgba(248,249,252,0.96)
  val textBody = Color(0xFFE6E7EE)
  val textDimmed = Color(0xB8A0A4B4) // ~0.72 alpha on #a0a4b4
  val textMuted = Color(0x99B4BCCF)

  val accentSelected = Color(0xFFF8F9FC)
  val accentSoft = Color(0xE6C8CEDA)

  val glassBorder = Color(0x1FFFFFFF) // rgba(255,255,255,0.12)
  val glassBorderSoft = Color(0x1AFFFFFF)

  val glassTileBgTop = Color(0x0EFFFFFF)
  val glassTileBgBottom = Color(0x1A000000)
  val glassTileBgBase = Color(0x38000000)

  val panelBgA = Color(0x0FFFFFFF)
  val panelBgB = Color(0x05FFFFFF)
  val panelBgC = Color(0x0F000000)
  val panelBgBase = Color(0x3806080C)

  val topbarBorder = Color(0x14FFFFFF)
}
