package example.zhttp.model

import zio.json.{DeriveJsonCodec, JsonCodec}

case class Rocket(rocket: String, payload: String)

object Rocket {
  implicit val codec: JsonCodec[Rocket] = DeriveJsonCodec.gen[Rocket]
}