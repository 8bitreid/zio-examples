package example

import zio.test._

import Assertion._

object ExampleZioSpec extends ZIOSpecDefault {
  def spec: Spec[Any, Nothing] =
    suite("Example test")(
      test("Your test")(
        assert("Your value")(Assertion.isNonEmptyString)
      )
    )
}
