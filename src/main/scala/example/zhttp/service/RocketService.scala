package example.zhttp.service

import example.zhttp.model.Rocket
import zio.{Task, UIO, ZIO, ZLayer}

trait RocketService {
  def rocketResponse: UIO[Rocket]
}

object RocketService {
  val layer = ZLayer.fromFunction(RockerServiceLive.apply _)
}

case class RockerServiceLive() extends RocketService {
  override def rocketResponse: UIO[Rocket] =
    ZIO.logInfo("rocket launch") *> ZIO.succeed(Rocket("🚀", "take off!"))
}
