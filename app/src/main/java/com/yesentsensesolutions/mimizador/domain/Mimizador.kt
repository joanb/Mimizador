package com.yesentsensesolutions.mimizador.domain

internal class Mimizador {

  fun mimi(normalText: String): String {
    return normalText
      .replace("a", "i")
      .replace("e", "i")
      .replace("o", "i")
      .replace("u", "i")
  }
}