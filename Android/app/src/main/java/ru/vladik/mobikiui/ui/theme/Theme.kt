package ru.vladik.mobikiui.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val AstrollScheme = darkColorScheme(
  background = AstrollColors.shellBaseBottom,
  surface = Color(0xF0121418),
  surfaceVariant = Color(0xCC0B0C10),
  primary = AstrollColors.accentSelected,
  onPrimary = Color(0xFF0B0C10),
  secondary = AstrollColors.textDimmed,
  onSecondary = AstrollColors.textPrimary,
  tertiary = AstrollColors.textMuted,
  onTertiary = AstrollColors.textPrimary,
  onBackground = AstrollColors.textBody,
  onSurface = AstrollColors.textBody,
  outline = AstrollColors.glassBorder,
  outlineVariant = AstrollColors.glassBorderSoft
)

@Composable
fun MobikiTheme(content: @Composable () -> Unit) {
  MaterialTheme(
    colorScheme = AstrollScheme,
    typography = androidx.compose.material3.Typography(),
    content = content
  )
}
