val scala3Version = "3.2.2"
val zioVersion    = "2.0.13"
val ZHTTPVersion  = "3.0.0-RC1"

Compile / run / fork := true

lazy val root = project
  .in(file("."))
  .settings(
    name         := "ZIO Example Project",
    version      := "0.1.0-SNAPSHOT",
    scalaVersion := scala3Version,
    libraryDependencies ++= Seq(
      "dev.zio"       %% "zio"                % zioVersion,
      "dev.zio"       %% "zio-http"           % ZHTTPVersion,
      "dev.zio"       %% "zio-json"           % "0.4.2",
      "dev.zio"       %% "zio-logging-slf4j2" % "2.1.12",
      "dev.zio"       %% "zio-test"           % "2.0.13" % Test,
      "dev.zio"       %% "zio-test-sbt"       % "2.0.13" % Test,
      "dev.zio"       %% "zio-test-magnolia"  % "2.0.13" % Test,
      "ch.qos.logback" % "logback-classic"    % "1.4.6"
    ),
    testFrameworks += new TestFramework("zio.test.sbt.ZTestFramework"),
    javaOptions += "-Dlogback.configurationFile=resources/logback.xml"
  )
