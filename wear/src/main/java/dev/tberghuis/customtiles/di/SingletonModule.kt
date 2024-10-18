package dev.tberghuis.customtiles.di

import android.content.Context
import androidx.datastore.preferences.preferencesDataStore

//
//// instead of this, do this in dagger hilt, meh

//
//
//@InstallIn(SingletonComponent::class)
//@Module
//object SingletonModule {
//  @Provides
//  @Singleton
//  fun provideDataStore(@ApplicationContext appContext: Context): DataStore<Preferences> {
//    return appContext.dataStore
//  }
//
//  @Provides
//  @Singleton
//  fun provideTileTextRepository(dataStore: DataStore<Preferences>): TileTextRepository {
//    return TileTextRepository(dataStore)
//  }
//
////  @Provides
////  @Singleton
////  fun provideErrorUiLayout(): LayoutElementBuilders.LayoutElement {
////    return LayoutElementBuilders.Box.Builder().setWidth(expand()).setHeight(expand())
////      .setHorizontalAlignment(HORIZONTAL_ALIGN_CENTER).setVerticalAlignment(VERTICAL_ALIGN_CENTER)
////      .addContent(
////        LayoutElementBuilders.Column.Builder().setWidth(expand())
////          .setHorizontalAlignment(HORIZONTAL_ALIGN_CENTER).setModifiers(
////            ModifiersBuilders.Modifiers.Builder().setBackground(
////              ModifiersBuilders.Background.Builder().setColor(argb(Color.DarkGray.toArgb())).build()
////            ).build()
////          ).addContent(
////            Text.Builder().setText("Glance Wear Tile Error").setFontStyle(
////              FontStyle.Builder().setSize(sp(18.toFloat())).setWeight(FONT_WEIGHT_BOLD).build()
////            ).build()
////          ).addContent(
////            Text.Builder().setText(
////              "Check the exact error using \"adb logcat\"," + " searching for \$GlanceWearTileTag"
////            ).setMaxLines(6).setMultilineAlignment(TEXT_ALIGN_CENTER).setFontStyle(
////              FontStyle.Builder().setSize(sp(14.toFloat())).build()
////            ).build()
////          ).build()
////      ).build()
////  }
//}