package example.zhttp

import io.netty.channel.EventLoopGroup
import zio.*
import zio.http.*
import example.zhttp.service.RocketService
import zio.json.*
import zio.logging.backend.SLF4J

object ZhttpServer extends ZIOAppDefault {
  override val bootstrap: ZLayer[ZIOAppArgs, Any, Any] = Runtime.removeDefaultLoggers >>> SLF4J.slf4j


  private val server: ZIO[RocketService with Server with MyHttpApp, Nothing, Unit] =
    for {
      httpApp <- ZIO.service[MyHttpApp]
      _       <- ZIO.log("starting server")
      _       <- Server.serve(httpApp.app)
    } yield ()

  override def run: ZIO[Any, Throwable, Unit] = server.provide(
    MyHttpApp.layer,
    RocketService.layer,
    Server.default
  )

  trait MyHttpApp {
    val app: Http[RocketService, Nothing, Request, Response]
  }

  private object MyHttpApp {
    val layer = ZLayer.fromFunction(MyHttpAppLive.apply _)
  }

  case class MyHttpAppLive(backendService: RocketService) extends MyHttpApp {
    val app: Http[RocketService, Nothing, Request, Response] =
      Http.collectZIO[Request] { case _ @Method.GET -> !! / "rocket" =>
        backendService.rocketResponse.map(rsp => Response.json(rsp.toJson))
      }
  }

}
