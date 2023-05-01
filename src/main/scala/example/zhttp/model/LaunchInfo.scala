package example.zhttp.model
import zio.json.{DeriveJsonCodec, JsonCodec}

case class LaunchInfo (launchCount: Int)

object LaunchInfo {
  implicit val codec: JsonCodec[LaunchInfo] = DeriveJsonCodec.gen[LaunchInfo]
}
