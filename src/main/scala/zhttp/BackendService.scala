package zhttp

import zio.Task
import zio.ZIO
import zio.ZLayer

trait BackendService {
  def rocketResponse: Task[Rocket]
}

object BackendService {
  val layer = ZLayer.fromFunction(BackendServiceLive.apply _)
}

case class BackendServiceLive() extends BackendService {
  override def rocketResponse: Task[Rocket] =
    ZIO.logInfo("rocket launch") *> ZIO.succeed(Rocket("🚀", "take off!"))
}
