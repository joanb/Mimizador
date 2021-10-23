package com.yesentsensesolutions.mimizador

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.yesentsensesolutions.mimizador.ui.theme.MimizadorTheme

class MimizadorActivity : ComponentActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContent {
      MimizadorTheme {
        // A surface container using the 'background' color from the theme
        Surface(color = MaterialTheme.colors.background) {
          Greeting()
        }
      }
    }
  }
}

@Composable
fun Greeting() {
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
        onClick = { /*TODO*/ }
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
  }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
  MimizadorTheme {
    Greeting()
  }
}