package zhttp

import zhttp.http.*
import zhttp.service.Server
import zio.*
import zio.json.*

object ZhttpServer extends ZIOAppDefault {
  override def run = server.provide(HttpApp.layer, BackendService.layer)

  private val server: ZIO[HttpApp, Throwable, Unit] =
    for {
      _       <- ZIO.log("starting server")
      httpApp <- ZIO.service[HttpApp]
      _       <- Server.start(8090, httpApp.app)
    } yield ()

  trait HttpApp {
    val app: Http[Any, Throwable, Request, Response]
  }

  object HttpApp {
    val layer = ZLayer.fromFunction(HttpAppLive.apply _)
  }

  case class HttpAppLive(backendService: BackendService) extends HttpApp {
    val app: Http[Any, Throwable, Request, Response] =
      Http.collectZIO[Request] { case _ @Method.GET -> !! / "rocket" =>
        backendService.rocketResponse.map(rsp => Response.json(rsp.toJson))
      }
  }

}
