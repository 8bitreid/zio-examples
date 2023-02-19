import zio.json.*

package object zhttp {
  case class Rocket(rocket: String, payload: String)

  object Rocket {
    implicit val codec: JsonCodec[Rocket] = DeriveJsonCodec.gen[Rocket]
  }
}
