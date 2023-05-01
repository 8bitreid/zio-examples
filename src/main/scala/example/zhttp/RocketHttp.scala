package example.zhttp

import example.zhttp.service.RocketService
import zio.ZLayer
import zio.http.*
import zio.*
import zio.json.*
import zio.http.Path.Segment.Root
import zio.http.{Http, Request, Response}

import scala.language.postfixOps

trait RocketHttp {
  val app: Http[RocketService, Nothing, Request, Response]
}

object RocketHttp {
  val liveHttp = ZLayer.fromFunction(RocketHttpLive.apply(_))
}

case class RocketHttpLive(service: RocketService) extends RocketHttp {
  val app: Http[RocketService, Nothing, Request, Response] = {
    Http.collectZIO {
      case _ @Method.POST -> !! / "launch" =>
        service.launchRocket.map(launch => Response.json(launch.toJson))
      case _ @Method.GET -> !! / "launch" =>
        service.launchInfo.map(info => Response.json(info.toJson))
    } @@ HttpAppMiddleware.timeout(1 second)
  }
}
