package io.github.iltotore.iron.example

import cats.effect._
import org.http4s.implicits._
import org.http4s.blaze.server.BlazeServerBuilder
import scala.concurrent.ExecutionContext

import io.github.iltotore.iron._, constraint.{given, _}, string.constraint.{given, _}

object Main extends IOApp {

  override def run(args: List[String]): IO[ExitCode] = BlazeServerBuilder[IO](ExecutionContext.global)
    .bindHttp(8080, "localhost")
    .withHttpApp(HttpServer.service.orNotFound)
    .serve
    .compile
    .drain
    .as(ExitCode.Success)

}