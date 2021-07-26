package io.github.iltotore.iron.example

import cats.data.NonEmptyChain
import cats.effect._
import org.http4s._, org.http4s.dsl.io._, org.http4s.implicits._, org.http4s.circe._

import io.circe.syntax._, io.circe.disjunctionCodecs._, io.circe.Encoder._, io.circe.Encoder

import io.github.iltotore.iron.constraint.IllegalValueError
import io.github.iltotore.iron.catsSupport._
import io.github.iltotore.iron.circe.given

object HttpServer {

  given EntityDecoder[IO, RefinedFieldNec[Account]] = accumulatingJsonOf[IO, RefinedFieldNec[Account]]

  val service = HttpRoutes.of[IO] {
    case request@POST -> Root / "register" =>
      request.as[RefinedFieldNec[Account]]
        .handleErrorWith(IO.raiseError)
        .map(_.asJson)
        .flatMap(Ok(_))

    case unknown =>
      NotFound()
  }
}