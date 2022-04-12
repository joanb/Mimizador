package com.yesentsensesolutions.mimizador.domain

fun String.mimize() =
  this
    .replace("[c][aou]".toRegex(), "ki")
    .replace("[c][áàéèóòú]".toRegex(), "kí")
    .replace("[q]u[aou]".toRegex(), "qüi")
    .replace("[q]u[áàéèóòú]".toRegex(), "qüí")
    .replace("[g]u[aou]".toRegex(), "güi")
    .replace("[g]u[áàéèóòú]".toRegex(), "güí")
    .replace("[áàéèóòú]".toRegex(), "í")
    .replace("[aeou]".toRegex(), "i")
