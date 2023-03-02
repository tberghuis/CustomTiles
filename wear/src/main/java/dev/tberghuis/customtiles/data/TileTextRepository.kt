package dev.tberghuis.customtiles.data

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

// todo move into another module "common"
class TileTextRepository(val dataStore: DataStore<Preferences>) {
  val tileTextFlow: Flow<String> = dataStore.data.map { preferences ->
    preferences[stringPreferencesKey("tile_text")] ?: "set tile text in phone app\n\ntap tile to update"
  }

  suspend fun updateTileText(tileText: String) {
    dataStore.edit { preferences ->
      preferences[stringPreferencesKey("tile_text")] = tileText
    }
  }
}
