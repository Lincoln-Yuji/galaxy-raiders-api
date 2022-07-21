 package galaxyraiders.core.game

import galaxyraiders.core.physics.Point2D
import galaxyraiders.core.physics.Vector2D

class Explosion(
  initialPosition: Point2D,
  initialVelocity: Vector2D,
  radius: Double,
  mass: Double
) :
  SpaceObject("Explosion", 'E', initialPosition, initialVelocity, radius, mass) {
    var is_triggered = true
    override fun move() {
      if (this is Explosion) {
        if (this.is_triggered) {
          this.is_triggered = false
        }
        else {
          this.radius = 0.0
        }
      }
    }
  }
