package example.zhttp

import io.netty.channel.EventLoopGroup
import zio.*
import zio.http.*
import example.zhttp.service.{
  RocketLaunchCount,
  RocketService,
  RocketServiceModule
}
import zio.json.*
import zio.logging.backend.SLF4J

import scala.language.postfixOps

object ZhttpServer extends ZIOAppDefault {
  // needed to use logback
  override val bootstrap: ZLayer[ZIOAppArgs, Any, Any] =
    Runtime.removeDefaultLoggers >>> SLF4J.slf4j

  private val server
      : ZIO[RocketService with Server with RocketHttp, Nothing, Unit] =
    for {
      httpApp <- ZIO.service[RocketHttp]
      _       <- ZIO.log(s"starting server at http://localhost:8080/rocket")
      _       <- Server.serve(httpApp.app)
    } yield ()

  override def run: ZIO[Any, Throwable, Unit] = server.provide(
    RocketHttp.liveHttp,
    RocketServiceModule.rocketService,
    Server.default
  )
}
