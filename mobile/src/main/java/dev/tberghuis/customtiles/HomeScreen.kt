package dev.tberghuis.customtiles

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import dev.tberghuis.customtiles.util.logd

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen() {
  Scaffold(
    // todo use R.app_name
    topBar = { TopAppBar(title = { Text("Custom Tile") }) },
  ) { paddingValues ->
    HomeScreenContent(paddingValues)
  }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreenContent(paddingValues: PaddingValues) {
  val vm = hiltViewModel<HomeScreenViewModel>()
  val context = LocalContext.current
  if (!vm.initialised.value) {
    // todo loading
    return
  }

  Column(
    modifier = Modifier
      .padding(paddingValues)
      .fillMaxSize(),
    verticalArrangement = Arrangement.Top,
    horizontalAlignment = Alignment.CenterHorizontally,
  ) {
    OutlinedTextField(
      value = vm.tileText.value,
      onValueChange = { newText ->
        vm.tileText.value = newText
      },
      modifier = Modifier
        .fillMaxWidth()
        .padding(20.dp)
        .height(300.dp),

//      minLines = 10, // https://stackoverflow.com/questions/69040571/android-compose-textfield-how-to-set-exact-3-lines
//      maxLines = 10,

      label = { Text("Tile Text") },
    )


    Button(
      onClick = {
        logd("Set Tile Text")
        vm.updateTileText(context)
      },
    ) {
      Text("Set Tile Text")
    }
    Spacer(modifier = Modifier.height(30.dp))

    Text("note: Tap Tile to refresh text")
  }

}

