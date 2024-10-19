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
//  composeOptions {
//    kotlinCompilerExtensionVersion = "1.4.2"
//  }
  packaging {
    resources {
      excludes += "/META-INF/{AL2.0,LGPL2.1}"
    }
  }
}

dependencies {

//  val compose_version = "1.3.3"
//  val wear_compose_version = "1.1.2"


  val composeBom = platform(libs.androidx.compose.bom)

  implementation(composeBom)
  androidTestImplementation(composeBom)
  debugImplementation(composeBom)


//  implementation("androidx.core:core-ktx:1.9.0")
  implementation(libs.androidx.core.ktx)
//  implementation("com.google.android.gms:play-services-wearable:18.0.0")
  implementation(libs.play.services.wearable)

//  implementation("androidx.compose.ui:ui")
  implementation(libs.androidx.compose.ui)

//  implementation("androidx.wear.compose:compose-material:$wear_compose_version")
  implementation(libs.wear.compose.material)
//  implementation("androidx.wear.compose:compose-foundation:$wear_compose_version")
  implementation(libs.wear.compose.foundation)
//  implementation("androidx.compose.ui:ui-tooling-preview")
  implementation(libs.androidx.compose.ui.tooling.preview)
//  implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.5.1")
  implementation(libs.androidx.lifecycle.runtime)
//  implementation("androidx.activity:activity-compose:1.6.1")
  implementation(libs.androidx.activity.compose)
//  androidTestImplementation("androidx.compose.ui:ui-test-junit4")
  implementation(libs.androidx.compose.ui.test.junit4)
//  debugImplementation("androidx.compose.ui:ui-tooling")
  implementation(libs.androidx.compose.ui.tooling)
//  debugImplementation("androidx.compose.ui:ui-test-manifest")
  implementation(libs.androidx.compose.ui.test.manifest)


//    wearApp project(":wear")


//    implementation "com.google.android.horologist:horologist-tiles:0.3.3"

  implementation(libs.androidx.glance.wear.tiles)


//    implementation "androidx.glance:glance-appwidget:1.0.0-alpha05"

//    implementation "com.google.accompanist:accompanist-pager:0.28.0"

//  implementation("androidx.datastore:datastore-preferences:1.0.0")
  implementation(libs.androidx.dataStore.preferences)

  implementation(libs.guava)
}

//kapt {
//  correctErrorTypes = true
//}
