package dev.tberghuis.customtiles.di

import android.content.Context
import androidx.datastore.preferences.preferencesDataStore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import dev.tberghuis.customtiles.data.TileTextRepository
import javax.inject.Singleton


val Context.dataStore by preferencesDataStore(
  name = "user_preferences",
)

@InstallIn(SingletonComponent::class)
@Module
object SingletonModule {

  @Provides
  @Singleton
  fun provideTileTextRepository(@ApplicationContext appContext: Context): TileTextRepository {
    return TileTextRepository(appContext.dataStore)
  }

}