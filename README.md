# Project example using Iron and Cats

This is an example project featuring [Cats](https://typelevel.org/cats/) (via [Http4s](https://http4s.org) +
[Circe](https://circe.github.io/circe/)) and [Iron](https://github.com/Iltotore/iron).

This project is a **minimal** REST API to show the synergy between Iron and Cats.

## Test the project

You can run this example using [Mill](https://com-lihaoyi.github.io/mill/mill/Intro_to_Mill.html):

```sh
millw main.run
```

Once the server started, you can send a POST request to `localhost:8080/register` with an Account-like JSON body:

```json
{
  "username": "Iltotore",
  "email": "me@myemail.com",
  "password": "Abc123"
}
```

- username should be alphanumeric
- email should be a valid (existing or not) email
- password should contain at least an upper, a lower and a number

You will receive a JSON response depending on the validity of your values.
<details>
<summary>If valid</summary>

```json
{
  "Valid": {
    "username": "Iltotore",
    "email": "me@myemail.com",
    "password": "Abc123"
  }
}
```

</details>

<details>

<summary>If invalid (bad password and email)</summary>

Original request:
```json
{
  "username": "Iltotore",
  "email": "memyemail.com",
  "password": "abc123"
}
```

Response:
```json
{
  "Invalid": [
    {
      "name": "email",
      "message": "Value should be an email"
    },
    {
      "name": "password",
      "message": "Value should contain at least an upper, a lower and a number"
    }
  ]
}
```

</details>