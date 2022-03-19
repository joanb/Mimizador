package com.yesentsensesolutions.mimizador.domain

fun String.mimize() =
  this
    .replace("a", "i")
    .replace("e", "i")
    .replace("o", "i")
    .replace("u", "i")