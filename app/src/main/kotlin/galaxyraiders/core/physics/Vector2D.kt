@file:Suppress("UNUSED_PARAMETER") // <- REMOVE
package galaxyraiders.core.physics

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties("unit", "normal", "degree", "magnitude")
data class Vector2D(val dx: Double, val dy: Double) {

  override fun toString(): String {
    return "Vector2D(dx=$dx, dy=$dy)"
  }

  val magnitude: Double
    get() = Math.sqrt(dx * dx + dy * dy)

  val radiant: Double
    get() = if (dy < 0.0) -1 * Math.acos(dx / magnitude) else Math.acos(dx / magnitude)

  val degree: Double
    get() = Math.toDegrees(radiant)

  val unit: Vector2D
    get() = Vector2D(dx / magnitude, dy / magnitude)

  val normal: Vector2D
    get() = Vector2D(dy / magnitude, -dx / magnitude)

  operator fun times(scalar: Double): Vector2D {
    return Vector2D(dx * scalar, dy * scalar)
  }

  operator fun div(scalar: Double): Vector2D {
    return Vector2D(dx / scalar, dy / scalar)
  }

  operator fun times(v: Vector2D): Double {
    return dx * v.dx + dy * v.dy
  }

  operator fun plus(v: Vector2D): Vector2D {
    return Vector2D(dx + v.dx, dy + v.dy)
  }

  operator fun plus(p: Point2D): Point2D {
    return Point2D(p.x + dx, p.y + dy)
  }

  operator fun unaryMinus(): Vector2D {
    return Vector2D(-dx, -dy)
  }

  operator fun minus(v: Vector2D): Vector2D {
    return Vector2D(dx - v.dx, dy - v.dy)
  }

  fun scalarProject(target: Vector2D): Double {
    return this.times(target) / target.magnitude
  }

  fun vectorProject(target: Vector2D): Vector2D {
    val projected_vector: Vector2D
    if      (target.dx != 0.0 && target.dy == 0.0) { // X axis
      projected_vector = Vector2D(dx, 0.0)
    }
    else if (target.dx == 0.0 && target.dy != 0.0) { // Y axis
      projected_vector = Vector2D(0.0, dy)
    }
    else { // Compute it "as it should be"
      projected_vector = target.times(this.times(target) / target.times(target))
    }
    return projected_vector
  }
}

operator fun Double.times(v: Vector2D): Vector2D {
  return Vector2D(this * v.dx, this * v.dy)
}
