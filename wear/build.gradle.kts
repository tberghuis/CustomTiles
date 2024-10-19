plugins {
  alias(libs.plugins.android.application)
  alias(libs.plugins.kotlin.android)
}

android {
  namespace = "dev.tberghuis.customtiles"
  compileSdk = 33

  defaultConfig {
    applicationId = "dev.tberghuis.customtiles"
    minSdk = 28
    targetSdk = 33
    versionCode = 10000
    versionName = "1.0"
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

  val compose_version = "1.3.3"
  val wear_compose_version = "1.1.2"

//  implementation("androidx.core:core-ktx:1.9.0")
  implementation(libs.androidx.core.ktx)
//  implementation("com.google.android.gms:play-services-wearable:18.0.0")
  implementation(libs.play.services.wearable)

  implementation("androidx.legacy:legacy-support-v4:1.0.0")
  implementation("androidx.recyclerview:recyclerview:1.2.1")
  implementation("androidx.compose.ui:ui:$compose_version")
  implementation("androidx.wear.compose:compose-material:$wear_compose_version")
  implementation("androidx.wear.compose:compose-foundation:$wear_compose_version")
  implementation("androidx.compose.ui:ui-tooling-preview:$compose_version")
  implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.5.1")
  implementation("androidx.activity:activity-compose:1.6.1")
  androidTestImplementation("androidx.compose.ui:ui-test-junit4:$compose_version")
  debugImplementation("androidx.compose.ui:ui-tooling:$compose_version")
  debugImplementation("androidx.compose.ui:ui-test-manifest:$compose_version")
//    wearApp project(":wear")


//    implementation "com.google.android.horologist:horologist-tiles:0.3.3"

  implementation("androidx.glance:glance-wear-tiles:1.0.0-alpha05")
//    implementation "androidx.glance:glance-appwidget:1.0.0-alpha05"

//    implementation "com.google.accompanist:accompanist-pager:0.28.0"

  implementation("androidx.datastore:datastore-preferences:1.0.0")


  implementation("com.google.guava:guava:31.0.1-android")
}

//kapt {
//  correctErrorTypes = true
//}
