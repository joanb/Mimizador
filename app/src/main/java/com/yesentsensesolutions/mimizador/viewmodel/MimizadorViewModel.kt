package com.yesentsensesolutions.mimizador.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yesentsensesolutions.mimizador.domain.mimize
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class MimizadorViewModel : ViewModel() {
  val state = MutableStateFlow<MimizadorState>(MimizadorState.Idle)

  fun sendEvent(intent: MimizadorIntent) {
    // TODO: improve coroutines treatment
    viewModelScope.launch {
      reduce(intent, state.value)
    }
  }

  suspend fun reduce(userIntent: MimizadorIntent, oldState: MimizadorState) {
    when (userIntent) {
      is MimizadorIntent.Mimize -> state.emit(MimizadorState.Mimized(userIntent.normalText.mimize()))
    }
  }
}

sealed class MimizadorState {
  object Idle : MimizadorState()
  data class Mimized(val mimizedText: String) : MimizadorState()
}

sealed class MimizadorIntent {
  // TODO: remove this intent
  object Idle : MimizadorIntent()
  data class Mimize(val normalText: String) : MimizadorIntent()
}