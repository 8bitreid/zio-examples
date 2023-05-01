package example.zhttp.service

import zio.ZLayer

object RocketServiceModule {
  val rocketService =
    ZLayer.make[RocketService](
      RocketLaunchCount.launchCount,
      RocketService.liveRockerService
    )

}
