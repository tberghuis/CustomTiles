package dev.tberghuis.customtiles.util

import android.util.Log
import dev.tberghuis.customtiles.BuildConfig

fun logd(s: String) {
  if (BuildConfig.DEBUG) {
    Log.d("xxx", s)
  }
}
