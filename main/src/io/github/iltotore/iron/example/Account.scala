package io.github.iltotore.iron.example

import io.github.iltotore.iron._, constraint.{given, _}, string.constraint.{given, _}, catsSupport._
import cats.implicits._, cats.syntax.apply._

import io.circe.{Decoder, Encoder}
import io.circe.generic.semiauto._
import io.github.iltotore.iron.circe.{given}

case class Account(username: String, email: String, password: String)

object Account {

  //Here, the Match[String] constraint isn't natively implemented in Iron and is only used as example.

  //Type aliases are not mandatory. They can be used for readability purpose.
  type Username = String ==> Alphanumeric
  type Email = String ==> (Match["^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$"] DescribedAs "Value should be an email")
  //At least one upper, one lower and one number
  type Password = String ==> (Match["^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d]+$"] DescribedAs
    "Value should contain at least an upper, a lower and a number")

  //Input values passed by the user
  def createAccount(username: Username, email: Email, password: Password): RefinedFieldNec[Account] = (
      username.toField("username").toValidatedNec,
      email.toField("email").toValidatedNec,
      password.toField("password").toValidatedNec
  ).mapN(Account.apply)

  inline given Decoder[RefinedFieldNec[Account]] =
    Decoder.forProduct3("username", "email", "password")(createAccount)

  inline given Encoder[Account] = deriveEncoder
}