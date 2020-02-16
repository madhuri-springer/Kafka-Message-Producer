
name := "KafkaMessageProducer"

version := "0.1"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

val scalaversion = "2.12.3"
scalaVersion := scalaversion

libraryDependencies ++= Seq(
  ws,
  guice,
  "com.github.pureconfig" %% "pureconfig" % "0.7.0",
  "com.typesafe.akka" %% "akka-stream-kafka" % "2.0.0-RC1",
  "org.slf4j" % "log4j-over-slf4j" % "1.7.25",
  "net.logstash.logback" % "logstash-logback-encoder" % "5.2",
  "ch.qos.logback" % "logback-classic" % "1.2.3",
  "org.scalatestplus.play" %% "scalatestplus-play" % "3.1.2" % Test,
  "io.github.embeddedkafka" %% "embedded-kafka" % "2.4.0" % Test,
  "org.mockito" % "mockito-core" % "1.9.5" % Test,
  "com.github.tomakehurst" % "wiremock-standalone" % "2.16.0" % Test
)
