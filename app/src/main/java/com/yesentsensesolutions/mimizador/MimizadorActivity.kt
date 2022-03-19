package com.yesentsensesolutions.mimizador

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import com.yesentsensesolutions.mimizador.ui.theme.MimizadorTheme
import com.yesentsensesolutions.mimizador.viewmodel.MimizadorIntent
import com.yesentsensesolutions.mimizador.viewmodel.MimizadorState
import com.yesentsensesolutions.mimizador.viewmodel.MimizadorViewModel

class MimizadorActivity : ComponentActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    val model: MimizadorViewModel by viewModels()
    setContent {
      MimizadorTheme {
        // A surface container using the 'background' color from the theme
        Surface(color = MaterialTheme.colors.background) {
          Greeting(model)
        }
      }
    }
  }
}

@Composable
fun Greeting(viewModel: MimizadorViewModel) {
  val uiState = viewModel.state.collectAsState()
  var input by remember { mutableStateOf("") }
  Column(
    Modifier
      .padding(8.dp)
      .fillMaxWidth()
  ) {
    OutlinedTextField(
      modifier = Modifier
        .padding(bottom = 8.dp)
        .fillMaxWidth(),
      value = input,
      onValueChange = { input = it },
      label = { Text("Text") }
    )
    Row(
      modifier = Modifier.fillMaxWidth(),
      horizontalArrangement = Arrangement.SpaceEvenly
    ) {
      Button(
        onClick = { viewModel.sendEvent(MimizadorIntent.Mimize("hola que tal")) }
      ) { Text(text = "Mimiza!") }
      Button(
        onClick = { /*TODO*/ }
      ) { Text(text = "Pega") }
      Button(
        onClick = { /*TODO*/ }
      ) {
        Text(text = "Copia")
      }
    }
    when (uiState.value) {
      is MimizadorState.Mimized -> MimizedText(mimizedText = (uiState.value as MimizadorState.Mimized).mimizedText)
      else -> {}
    }
  }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
  MimizadorTheme {
    Greeting(MimizadorViewModel())
  }
}

@Composable
fun MimizedText(mimizedText: String) {
  Text(text = mimizedText)
}