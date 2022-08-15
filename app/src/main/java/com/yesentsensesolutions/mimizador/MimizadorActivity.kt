@file:Suppress("EXPERIMENTAL_API_USAGE_FUTURE_ERROR")

package com.yesentsensesolutions.mimizador

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ClipboardManager
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.yesentsensesolutions.mimizador.ui.theme.MimizadorTheme
import com.yesentsensesolutions.mimizador.viewmodel.MimizadorIntent
import com.yesentsensesolutions.mimizador.viewmodel.MimizadorState
import com.yesentsensesolutions.mimizador.viewmodel.MimizadorViewModel
import kotlin.time.ExperimentalTime

@ExperimentalTime
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

@OptIn(ExperimentalTime::class)
@Composable
fun Greeting(viewModel: MimizadorViewModel) {
  val uiState = viewModel.state.collectAsState()
  var input by remember { mutableStateOf("") }
  val clipboardManager: ClipboardManager = LocalClipboardManager.current
  viewModel.normalText.value = input
  Column(
    Modifier
      .padding(8.dp)
      .fillMaxWidth()
      .fillMaxHeight(),
    verticalArrangement = Arrangement.Center
  ) {
    OutlinedTextField(
      modifier = Modifier
        .padding(bottom = 8.dp)
        .fillMaxWidth(),
      value = input,
      onValueChange = { input = it },
      label = { Text("Text") },
      trailingIcon = {
        Icon(
          Icons.Filled.Clear,
          contentDescription = "sdsd",
          modifier = Modifier
            .offset(x = 10.dp)
            .clickable {
              input = ""
            }
        )
      }
    )
    Row(
      modifier = Modifier.fillMaxWidth(),
      horizontalArrangement = Arrangement.SpaceEvenly
    ) {
      Button(
        onClick = { viewModel.sendEvent(MimizadorIntent.Mimize("hola que tal")) }
      ) { Text(text = "Mimiza!") }
      Button(
        onClick = {
          clipboardManager.getText()?.text?.let {
            input += it
          }
        }
      ) { Text(text = "Pega") }
      Button(
        onClick = { clipboardManager.setText(AnnotatedString((uiState.value as MimizadorState.Mimized).mimizedText)) }
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

@OptIn(ExperimentalTime::class)
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