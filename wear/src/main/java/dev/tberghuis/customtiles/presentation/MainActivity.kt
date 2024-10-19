package dev.tberghuis.customtiles.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.wear.compose.material.MaterialTheme
import androidx.wear.compose.material.Text
import dev.tberghuis.customtiles.presentation.theme.CustomTilesTheme

class MainActivity : ComponentActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContent {
      WearApp()
    }
  }
}

@Composable
fun WearApp() {
  CustomTilesTheme {
    Column(
      modifier = Modifier
        .fillMaxSize()
        .background(MaterialTheme.colors.background),
      verticalArrangement = Arrangement.Center
    ) {
      Text(
        modifier = Modifier.fillMaxWidth(),
        textAlign = TextAlign.Center,
        color = MaterialTheme.colors.primary,
        text = "hello world"
      )
    }
  }
}