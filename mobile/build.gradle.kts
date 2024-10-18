plugins {
  alias(libs.plugins.android.application)
  alias(libs.plugins.kotlin.android)
//    id 'kotlin-kapt'
}

android {
  namespace = "dev.tberghuis.customtiles"
  compileSdk = 33

  defaultConfig {
    applicationId = "dev.tberghuis.customtiles"
    minSdk = 28
    targetSdk = 33
    versionCode = 3
    versionName = "1.2"

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
  }
  composeOptions {
    kotlinCompilerExtensionVersion = "1.4.2"
  }
  packagingOptions {
    resources {
      excludes += "/META-INF/{AL2.0,LGPL2.1}"
    }
  }
}

dependencies {
  implementation("androidx.core:core-ktx:1.9.0")
  implementation("com.google.android.gms:play-services-wearable:18.0.0")


  val composeBom = platform("androidx.compose:compose-bom:2023.01.00")
  implementation(composeBom)
  androidTestImplementation(composeBom)

  implementation("androidx.compose.material3:material3")
  implementation("androidx.compose.ui:ui-tooling-preview")
  debugImplementation("androidx.compose.ui:ui-tooling")
  androidTestImplementation("androidx.compose.ui:ui-test-junit4")
  debugImplementation("androidx.compose.ui:ui-test-manifest")

  implementation("androidx.activity:activity-compose:1.6.1")
  implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.5.1")

  implementation("org.jetbrains.kotlinx:kotlinx-coroutines-play-services:1.6.4")


  // todo do i comment this out???
//    wearApp project(":wear")


  implementation("androidx.datastore:datastore-preferences:1.0.0")
}