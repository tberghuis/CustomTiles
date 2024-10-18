package dev.tberghuis.customtiles

import android.app.Application
import android.content.Context
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.google.android.gms.wearable.PutDataMapRequest
import com.google.android.gms.wearable.Wearable
import dev.tberghuis.customtiles.util.logd
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

class HomeScreenViewModel(
  application: Application,
) : AndroidViewModel(application) {
  val tileText = mutableStateOf<String>("")
  val initialised = mutableStateOf(false)

  val snackbarHostState = SnackbarHostState()
  private val tileTextRepository = application.provideTileTextRepository()

  init {

    viewModelScope.launch {
      tileText.value = tileTextRepository.tileTextFlow.first()
      initialised.value = true
    }

  }

  @SuppressWarnings("VisibleForTests")
  fun updateTileText(context: Context) {
    val dataClient = Wearable.getDataClient(context)
    val request = PutDataMapRequest.create("/tile_text").apply {
      dataMap.putString("tile_text", tileText.value)
    }.asPutDataRequest().setUrgent()
    viewModelScope.launch {
      try {
        val result = dataClient.putDataItem(request).await()
        logd("result $result")
        snackbarHostState.showSnackbar("Tile updated")
      } catch (cancellationException: CancellationException) {
        throw cancellationException
      } catch (exception: Exception) {
        logd("Saving DataItem failed: $exception")
        snackbarHostState.showSnackbar("$exception")
      }
    }
    viewModelScope.launch {
      tileTextRepository.updateTileText(tileText.value)
    }
  }

//  @SuppressWarnings("VisibleForTests")
//  fun startWearActivity(context: Context) {
//    val capabilityClient = Wearable.getCapabilityClient(context)
//    val messageClient = Wearable.getMessageClient(context)
//    viewModelScope.launch {
//      try {
//        val nodes =
//          capabilityClient.getCapability("wear", CapabilityClient.FILTER_REACHABLE).await().nodes
//        // Send a message to all nodes in parallel
//        nodes.map { node ->
//          async {
//            messageClient.sendMessage(node.id, "/start-activity", byteArrayOf()).await()
//          }
//        }.awaitAll()
//        Log.d("xxx", "Starting activity requests sent successfully")
//      } catch (cancellationException: CancellationException) {
//        throw cancellationException
//      } catch (exception: Exception) {
//        Log.d("xxx", "Starting activity failed: $exception")
//      }
//    }
//  }
}