package dev.tberghuis.customtiles

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStoreFile
import androidx.glance.GlanceComposable
import androidx.glance.GlanceId
import androidx.glance.GlanceModifier
import androidx.glance.action.clickable
import androidx.glance.currentState
import androidx.glance.layout.Alignment
import androidx.glance.layout.Column
import androidx.glance.layout.fillMaxSize
import androidx.glance.state.GlanceStateDefinition
import androidx.glance.text.Text
import androidx.glance.wear.tiles.GlanceTileService
import androidx.glance.wear.tiles.action.ActionCallback
import androidx.glance.wear.tiles.action.actionRunCallback
import dagger.hilt.android.AndroidEntryPoint
import dev.tberghuis.customtiles.util.logd
import java.io.File
import javax.inject.Inject

@AndroidEntryPoint(GlanceTileService::class)
class CustomTileService : Hilt_CustomTileService() {

  @Inject
  lateinit var dataStore: DataStore<Preferences>

  override val stateDefinition = CustomTilesPreferencesGlanceStateDefinition(lazy { dataStore })

  private val tileTextKey = stringPreferencesKey("tile_text")

  init {
    logd("CustomTileService init")
  }

  @Composable
  @GlanceComposable
  override fun Content() {
//    val glanceId = LocalGlanceId.current

    Column(
      modifier = GlanceModifier.fillMaxSize().clickable(
        actionRunCallback<UpdateAction>()
      ),
      horizontalAlignment = Alignment.CenterHorizontally,
      verticalAlignment = Alignment.CenterVertically
    ) {
      Text(currentState(tileTextKey) ?: "set tile text in phone app\n\ntap tile to update")
    }
  }
}

class UpdateAction : ActionCallback {

  // this works... how retarded
  override suspend fun onAction(context: Context, glanceId: GlanceId) {
    logd("does the action callback work???")
  }
}

// this is a hack that somehow worked
class CustomTilesPreferencesGlanceStateDefinition(private val lazyDataStore: Lazy<DataStore<Preferences>>) :
  GlanceStateDefinition<Preferences> {
  override fun getLocation(context: Context, fileKey: String): File =
    context.preferencesDataStoreFile("user_preferences")

  override suspend fun getDataStore(context: Context, fileKey: String): DataStore<Preferences> =
    lazyDataStore.value
}