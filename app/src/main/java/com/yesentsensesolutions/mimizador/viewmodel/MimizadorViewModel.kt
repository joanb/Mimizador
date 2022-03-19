package com.yesentsensesolutions.mimizador.viewmodel

import android.os.MessageQueue
import androidx.lifecycle.ViewModel

internal class MimizadorViewModel : ViewModel() {
}

internal sealed class MimizadorState() {
  object Idle : MimizadorState()
  data class Mimized(val normalText: String, val mimizedText: String) : MimizadorState()
}