package dev.tberghuis.customtiles.di

import android.content.Context
import androidx.datastore.preferences.preferencesDataStore
import androidx.wear.tiles.ColorBuilders.argb
import androidx.wear.tiles.DimensionBuilders.expand
import androidx.wear.tiles.LayoutElementBuilders
import androidx.wear.tiles.LayoutElementBuilders.HORIZONTAL_ALIGN_CENTER
import androidx.wear.tiles.LayoutElementBuilders.VERTICAL_ALIGN_CENTER
import androidx.wear.tiles.ModifiersBuilders
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import dev.tberghuis.customtiles.data.TileTextRepository
import javax.inject.Singleton
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.wear.tiles.ColorBuilders.argb
import androidx.wear.tiles.DimensionBuilders.expand
import androidx.wear.tiles.DimensionBuilders.sp
import androidx.wear.tiles.LayoutElementBuilders.LayoutElement
import androidx.wear.tiles.LayoutElementBuilders.Box
import androidx.wear.tiles.LayoutElementBuilders.Column
import androidx.wear.tiles.LayoutElementBuilders.FONT_WEIGHT_BOLD
import androidx.wear.tiles.LayoutElementBuilders.FontStyle
import androidx.wear.tiles.LayoutElementBuilders.HORIZONTAL_ALIGN_CENTER
import androidx.wear.tiles.LayoutElementBuilders.VERTICAL_ALIGN_CENTER
import androidx.wear.tiles.LayoutElementBuilders.TEXT_ALIGN_CENTER
import androidx.wear.tiles.LayoutElementBuilders.Text
import androidx.wear.tiles.ModifiersBuilders.Background
import androidx.wear.tiles.ModifiersBuilders.Modifiers
import dev.tberghuis.customtiles.CustomTilesPreferencesGlanceStateDefinition

// instead of this, do this in dagger hilt, meh
val Context.dataStore by preferencesDataStore(
  name = "user_preferences",
)


@InstallIn(SingletonComponent::class)
@Module
object SingletonModule {
  @Provides
  @Singleton
  fun provideDataStore(@ApplicationContext appContext: Context): DataStore<Preferences> {
    return appContext.dataStore
  }

  @Provides
  @Singleton
  fun provideTileTextRepository(dataStore: DataStore<Preferences>): TileTextRepository {
    return TileTextRepository(dataStore)
  }

//  @Provides
//  @Singleton
//  fun provideErrorUiLayout(): LayoutElementBuilders.LayoutElement {
//    return LayoutElementBuilders.Box.Builder().setWidth(expand()).setHeight(expand())
//      .setHorizontalAlignment(HORIZONTAL_ALIGN_CENTER).setVerticalAlignment(VERTICAL_ALIGN_CENTER)
//      .addContent(
//        LayoutElementBuilders.Column.Builder().setWidth(expand())
//          .setHorizontalAlignment(HORIZONTAL_ALIGN_CENTER).setModifiers(
//            ModifiersBuilders.Modifiers.Builder().setBackground(
//              ModifiersBuilders.Background.Builder().setColor(argb(Color.DarkGray.toArgb())).build()
//            ).build()
//          ).addContent(
//            Text.Builder().setText("Glance Wear Tile Error").setFontStyle(
//              FontStyle.Builder().setSize(sp(18.toFloat())).setWeight(FONT_WEIGHT_BOLD).build()
//            ).build()
//          ).addContent(
//            Text.Builder().setText(
//              "Check the exact error using \"adb logcat\"," + " searching for \$GlanceWearTileTag"
//            ).setMaxLines(6).setMultilineAlignment(TEXT_ALIGN_CENTER).setFontStyle(
//              FontStyle.Builder().setSize(sp(14.toFloat())).build()
//            ).build()
//          ).build()
//      ).build()
//  }
}