package dev.tberghuis.customtiles

import android.annotation.SuppressLint
import com.google.android.gms.wearable.DataEventBuffer
import com.google.android.gms.wearable.DataMapItem
import com.google.android.gms.wearable.MessageEvent
import com.google.android.gms.wearable.WearableListenerService
import dev.tberghuis.customtiles.data.TileTextRepository
import dev.tberghuis.customtiles.util.logd
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch

class DataLayerListenerService :
  WearableListenerService() {
  private lateinit var tileTextRepository: TileTextRepository

  override fun onCreate() {
    super.onCreate()
    tileTextRepository = provideTileTextRepository()
  }

  private val scope = CoroutineScope(SupervisorJob() + Dispatchers.Main.immediate)

  init {
    logd("DataLayerListenerService init")
  }

  @SuppressLint("VisibleForTests")
  override fun onDataChanged(dataEvents: DataEventBuffer) {
    logd("onDataChanged $dataEvents")
    super.onDataChanged(dataEvents)
    logd("onDataChanged $dataEvents")

    dataEvents.forEach { dataEvent ->
      val uri = dataEvent.dataItem.uri
      when (uri.path) {
        "/tile_text" -> {

          val dataMap = DataMapItem.fromDataItem(dataEvent.dataItem).dataMap
          val tileText = dataMap.getString("tile_text")

//          val tileText = dataEvent.dataItem.data.contentToString()
          logd("tileText $tileText")

          scope.launch {
            tileTextRepository.updateTileText("$tileText")
          }
        }
      }
    }


  }

  // todo remove, plus remove MESSAGE_RECEIVED from manifest
  override fun onMessageReceived(messageEvent: MessageEvent) {
    super.onMessageReceived(messageEvent)
    logd("onMessageReceived")
  }
}