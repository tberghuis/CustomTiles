package dev.tberghuis.customtiles.util

import android.util.Log
import dev.tberghuis.customtiles.BuildConfig

// todo can i configure gradle to remove "logd" calls from release builds at compile time

fun logd(s: String) {
  if (BuildConfig.DEBUG) {
    Log.d("xxx", s)
  }
}
