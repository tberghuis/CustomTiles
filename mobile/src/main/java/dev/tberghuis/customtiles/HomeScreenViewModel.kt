package dev.tberghuis.customtiles

import android.content.Context
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.android.gms.wearable.PutDataMapRequest
import com.google.android.gms.wearable.Wearable
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.tberghuis.customtiles.data.TileTextRepository
import dev.tberghuis.customtiles.util.logd
import javax.inject.Inject
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

@HiltViewModel
class HomeScreenViewModel
@Inject constructor(
  private val tileTextRepository: TileTextRepository
) : ViewModel() {
  val tileText = mutableStateOf<String>("")
  val initialised = mutableStateOf(false)

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
      } catch (cancellationException: CancellationException) {
        throw cancellationException
      } catch (exception: Exception) {
        logd("Saving DataItem failed: $exception")
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