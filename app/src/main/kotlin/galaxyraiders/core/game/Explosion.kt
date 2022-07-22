 package galaxyraiders.core.game

import galaxyraiders.core.physics.Point2D
import galaxyraiders.core.physics.Vector2D

class Explosion(
  initialPosition: Point2D,
  initialVelocity: Vector2D,
  radius: Double,
  mass: Double
) :
  SpaceObject("Explosion", 'x', initialPosition, initialVelocity, radius, mass) {
    var visible_frame_count = 90
    // Efeito de explosão fica visível por um curto período
    override fun move() {
      super.move()
      if (this.visible_frame_count > 0) {
        this.visible_frame_count -= 1
      }
      else {
        // Esconde a explosão
        this.radius = 0.0
      }
    }
  }
