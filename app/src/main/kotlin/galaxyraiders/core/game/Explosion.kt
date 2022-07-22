package galaxyraiders.core.game

import galaxyraiders.core.physics.Point2D
import galaxyraiders.core.physics.Vector2D

private const val EXPLOSION_FRAME_COUNT: Int = 90

class Explosion(
  initialPosition: Point2D,
  initialVelocity: Vector2D,
  radius: Double,
  mass: Double
) :
  SpaceObject("Explosion", 'x', initialPosition, initialVelocity, radius, mass) {
  var visibleFrameCount = EXPLOSION_FRAME_COUNT
  // Efeito de explosão fica visível por um curto período
  override fun move() {
    super.move()
    if (this.visibleFrameCount > 0) {
      this.visibleFrameCount -= 1
    } else {
      // Esconde a explosão
      this.radius = 0.0
    }
  }
}
