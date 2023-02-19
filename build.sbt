val scala3Version = "3.2.2"
val zioVersion    = "2.0.9"
val ZHTTPVersion  = "2.0.0-RC11"

lazy val root = project
  .in(file("."))
  .settings(
    name         := "ZIO Example Project",
    version      := "0.1.0-SNAPSHOT",
    scalaVersion := scala3Version,
    libraryDependencies ++= Seq(
      "dev.zio"       %% "zio"          % zioVersion,
      "dev.zio"       %% "zio-json"     % "0.4.2",
      "dev.zio"       %% "zio-test"     % zioVersion,
      "dev.zio"       %% "zio-test-sbt" % zioVersion,
      "io.d11"        %% "zhttp"        % ZHTTPVersion,
      "org.scalameta" %% "munit"        % "0.7.29" % Test
    )
  )
