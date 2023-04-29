val scala3Version = "3.2.2"
val zioVersion    = "2.0.13"
val ZHTTPVersion  = "3.0.0-RC1"

lazy val root = project
  .in(file("."))
  .settings(
    name         := "ZIO Example Project",
    version      := "0.1.0-SNAPSHOT",
    scalaVersion := scala3Version,
    libraryDependencies ++= Seq(
      "dev.zio"        %% "zio"                % zioVersion,
      "dev.zio"        %% "zio-http"           % ZHTTPVersion,
      "dev.zio"        %% "zio-json"           % "0.4.2",
      "dev.zio"        %% "zio-logging-slf4j2" % "2.1.12",
      "dev.zio"        %% "zio-test"           % zioVersion,
      "dev.zio"        %% "zio-test-sbt"       % zioVersion,
      "ch.qos.logback" % "logback-classic"    % "1.4.6",
      "org.scalameta"  %% "munit"              % "0.7.29" % Test
    ),
    javaOptions ++= Seq(
      "-Dlogback.configurationFile=resources/logback.xml"
    )
  )
