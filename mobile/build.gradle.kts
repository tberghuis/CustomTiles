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
    versionCode = 6
    versionName = "1.3.2"

    testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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
  implementation(libs.androidx.compose.material3)
  implementation(libs.androidx.compose.ui.tooling.preview)
  implementation(libs.androidx.activity.compose)
  implementation(libs.androidx.lifecycle.viewModelCompose)
  implementation(libs.kotlinx.coroutines.play.services)
  implementation(libs.androidx.dataStore.preferences)

  debugImplementation(libs.androidx.compose.ui.tooling)
  debugImplementation(libs.androidx.compose.ui.test.manifest)

  androidTestImplementation(libs.androidx.compose.ui.test.junit4)

  // todo do i comment this out???
//    wearApp project(":wear")
}