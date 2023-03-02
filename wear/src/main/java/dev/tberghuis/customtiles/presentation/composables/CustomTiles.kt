package dev.tberghuis.customtiles.presentation.composables

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.glance.GlanceId
import androidx.glance.GlanceModifier
import androidx.glance.action.Action
import androidx.glance.action.clickable
import androidx.glance.layout.Alignment
import androidx.glance.layout.Box
import androidx.glance.layout.fillMaxSize
import androidx.glance.text.Text
import androidx.glance.wear.tiles.action.ActionCallback
import androidx.glance.wear.tiles.action.actionRunCallback
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

object FakeViewModel {
  val pageList: StateFlow<List<Int>> = MutableStateFlow(listOf(1, 2, 3))
  val currentPage = MutableStateFlow(0)

}

object BoxClickActionCallback : ActionCallback {
  override suspend fun onAction(context: Context, glanceId: GlanceId) {
    FakeViewModel.currentPage.value++
  }

}


@Composable
fun CustomTiles() {
  val currentPage = FakeViewModel.currentPage.collectAsState(initial = 0)

  Box(
    GlanceModifier.fillMaxSize().clickable(actionRunCallback<BoxClickActionCallback>()),
    contentAlignment = Alignment.Center
  ) {
    Text("hello world, currentPage: $currentPage")
  }
}