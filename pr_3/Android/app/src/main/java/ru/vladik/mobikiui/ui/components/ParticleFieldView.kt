package ru.vladik.mobikiui.ui.components

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color as AndroidColor
import android.graphics.Paint
import android.view.Choreographer
import android.view.View
import kotlin.math.sqrt
import kotlin.random.Random

private data class Particle(
  var x: Float,
  var y: Float,
  val r: Float,
  val vx: Float,
  val vy: Float,
  val a: Float
)

/**
 * Сетка частиц и линий как в `astroll-client/src/pages/BackgroundFx.tsx`.
 */
class ParticleFieldView(context: Context) : View(context) {

  private val paintDot = Paint(Paint.ANTI_ALIAS_FLAG).apply { style = Paint.Style.FILL }
  private val paintLine = Paint(Paint.ANTI_ALIAS_FLAG).apply { style = Paint.Style.STROKE }
  private var particles = emptyList<Particle>()
  private var w = 0f
  private var h = 0f
  private val choreographer = Choreographer.getInstance()
  private var running = false

  private val frameCallback = object : Choreographer.FrameCallback {
    override fun doFrame(frameTimeNanos: Long) {
      if (!running) return
      step()
      invalidate()
      choreographer.postFrameCallback(this)
    }
  }

  var pointCount: Int = 200
    set(value) {
      field = value.coerceIn(0, 400)
      initParticles()
    }

  override fun onAttachedToWindow() {
    super.onAttachedToWindow()
    running = true
    choreographer.postFrameCallback(frameCallback)
  }

  override fun onDetachedFromWindow() {
    running = false
    choreographer.removeFrameCallback(frameCallback)
    super.onDetachedFromWindow()
  }

  override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
    this.w = w.toFloat()
    this.h = h.toFloat()
    initParticles()
  }

  private fun initParticles() {
    if (w <= 0 || h <= 0) return
    val rnd = Random.Default
    particles = List(pointCount) {
      Particle(
        x = rnd.nextFloat() * w,
        y = rnd.nextFloat() * h,
        r = rnd.nextFloat() * (2.4f - 1.1f) + 1.1f,
        vx = rnd.nextFloat() * 0.24f - 0.12f,
        vy = rnd.nextFloat() * 0.18f - 0.09f,
        a = rnd.nextFloat() * (0.24f - 0.08f) + 0.08f
      )
    }
  }

  private fun step() {
    val pts = particles
    for (p in pts) {
      p.x += p.vx
      p.y += p.vy
      if (p.x < -20f) p.x = w + 20f
      if (p.x > w + 20f) p.x = -20f
      if (p.y < -20f) p.y = h + 20f
      if (p.y > h + 20f) p.y = -20f
    }
  }

  override fun onDraw(canvas: Canvas) {
    super.onDraw(canvas)
    val pts = particles
    for (p in pts) {
      paintDot.color = AndroidColor.argb((p.a * 255f).toInt().coerceIn(0, 255), 235, 235, 240)
      canvas.drawCircle(p.x, p.y, p.r, paintDot)
    }
    for (i in pts.indices) {
      for (j in i + 1 until pts.size) {
        val a = pts[i]
        val b = pts[j]
        val dx = a.x - b.x
        val dy = a.y - b.y
        val dist = sqrt(dx * dx + dy * dy)
        if (dist > 140f) continue
        val alpha = (1f - dist / 140f) * 0.052f
        paintLine.strokeWidth = 1f
        paintLine.color = AndroidColor.argb((alpha * 255f).toInt().coerceIn(0, 255), 235, 235, 240)
        canvas.drawLine(a.x, a.y, b.x, b.y, paintLine)
      }
    }
  }
}
