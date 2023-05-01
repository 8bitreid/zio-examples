package example.zhttp.service

import example.zhttp.model.{LaunchInfo, Rocket}
import zio.{Ref, Task, UIO, ZIO, ZLayer}

trait RocketService {
  def launchRocket: UIO[Rocket]
  def launchInfo: UIO[LaunchInfo]
}

object RocketService {
  val liveRockerService = ZLayer.fromFunction(RocketServiceLive.apply(_))
}

object RocketLaunchCount {
  val launchCount = ZLayer(Ref.make(0))
}
case class RocketServiceLive(launchCount: Ref[Int]) extends RocketService {
  override def launchRocket: UIO[Rocket] =
    for {
      totalLaunches <- launchCount.updateAndGet(_ + 1)
      _             <- ZIO.logInfo(s"rocket launch number $totalLaunches")
    } yield Rocket("🚀", s"take off number $totalLaunches")

  override def launchInfo: UIO[LaunchInfo] =
    for {
      totalLaunches <- launchCount.get
    } yield LaunchInfo(totalLaunches)
}
