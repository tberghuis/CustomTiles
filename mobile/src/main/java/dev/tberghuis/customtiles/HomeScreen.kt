package dev.tberghuis.customtiles

import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import dev.tberghuis.customtiles.util.logd
import kotlinx.coroutines.delay

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
  vm: HomeScreenViewModel = viewModel()
) {

  Scaffold(
    // todo use R.app_name
    topBar = {
      TopAppBar(title = { Text("Custom Tile") })
    }, snackbarHost = {
      SnackbarHost(vm.snackbarHostState) { data ->
        Snackbar(
          modifier = Modifier.padding(10.dp),
        ) {
          Text(data.visuals.message)
        }
      }
    }) { paddingValues ->
    HomeScreenContent(paddingValues)
  }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreenContent(
  paddingValues: PaddingValues, vm: HomeScreenViewModel = viewModel()
) {
  val context = LocalContext.current
  if (!vm.initialised.value) {
    // todo loading
    return
  }


  val focusManager = LocalFocusManager.current


  Column(
    modifier = Modifier
      .padding(paddingValues)
      .fillMaxSize()
      .verticalScroll(rememberScrollState())
      .pointerInput(Unit) {
        detectTapGestures(onTap = {
          focusManager.clearFocus()
        })
      },
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

        focusManager.clearFocus()

        vm.updateTileText(context)
      },
    ) {
      Text("Set Tile Text")
    }
    Spacer(modifier = Modifier.height(30.dp))
    Text("note: Tap Tile to refresh text")
    Spacer(modifier = Modifier.height(20.dp))
  }
}