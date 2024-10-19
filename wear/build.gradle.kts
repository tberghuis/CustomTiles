plugins {
  alias(libs.plugins.android.application)
  alias(libs.plugins.kotlin.android)
  alias(libs.plugins.compose)
}

android {
  namespace = "dev.tberghuis.customtiles"
  compileSdk = 34

  defaultConfig {
    applicationId = "dev.tberghuis.customtiles"
    minSdk = 28
    targetSdk = 34
    versionCode = 10007
    versionName = "1.3.3"
    vectorDrawables {
      useSupportLibrary = true
    }
  }

  buildTypes {
    release {
      isMinifyEnabled = false
      proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
    }
  }
  compileOptions {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
  }
  kotlinOptions {
    jvmTarget = "1.8"
    freeCompilerArgs += "-opt-in=kotlin.RequiresOptIn"
  }
  buildFeatures {
    compose = true
    buildConfig = true
  }
  packaging {
    resources {
      excludes += "/META-INF/{AL2.0,LGPL2.1}"
    }
  }
}

dependencies {
  val composeBom = platform(libs.androidx.compose.bom)
  implementation(composeBom)
  androidTestImplementation(composeBom)
  debugImplementation(composeBom)

  implementation(libs.androidx.core.ktx)
  implementation(libs.play.services.wearable)
  implementation(libs.androidx.compose.ui)
  implementation(libs.wear.compose.material)
  implementation(libs.wear.compose.foundation)
  implementation(libs.androidx.compose.ui.tooling.preview)
  implementation(libs.androidx.lifecycle.runtime)
  implementation(libs.androidx.activity.compose)
  implementation(libs.androidx.glance.wear.tiles)
  implementation(libs.androidx.dataStore.preferences)
  implementation(libs.guava)

  androidTestImplementation(libs.androidx.compose.ui.test.junit4)

  debugImplementation(libs.androidx.compose.ui.tooling)
  debugImplementation(libs.androidx.compose.ui.test.manifest)

//    wearApp project(":wear")
}