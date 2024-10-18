package dev.tberghuis.customtiles

import android.app.Application
import android.content.Context
import androidx.datastore.preferences.preferencesDataStore
import dev.tberghuis.customtiles.data.TileTextRepository

val Context.dataStore by preferencesDataStore(
  name = "user_preferences",
)

class CustomTilesApplication : Application() {
  lateinit var tileTextRepository: TileTextRepository

  override fun onCreate() {
    super.onCreate()
    createTileTextRepository()
  }

  private fun createTileTextRepository() {
    tileTextRepository = TileTextRepository(dataStore)
  }
}

fun Context.provideTileTextRepository(): TileTextRepository {
  return (applicationContext as CustomTilesApplication).tileTextRepository
}