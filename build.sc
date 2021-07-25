import mill._, scalalib._

object main extends ScalaModule {

  def scalaVersion = "3.0.0"

  def http4sVersion = "0.23.0-RC1"

  def ivyDeps = Agg(
    ivy"org.http4s::http4s-core:$http4sVersion",
    ivy"org.http4s::http4s-dsl:$http4sVersion",
    ivy"org.http4s::http4s-blaze-server:$http4sVersion",
    ivy"org.http4s::http4s-circe:$http4sVersion",

    ivy"io.circe::circe-core:0.14.1",
    ivy"io.circe::circe-generic:0.14.1",

    ivy"io.github.iltotore::iron:1.1",
    ivy"io.github.iltotore::iron-string:1.1-0.1.0",
    ivy"io.github.iltotore::iron-cats:1.1-0.1.0",
    ivy"io.github.iltotore::iron-circe:1.1-0.1.0"
  )
}