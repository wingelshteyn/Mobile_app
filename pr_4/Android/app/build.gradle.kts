plugins {
  id("com.android.application")
  id("org.jetbrains.kotlin.android")
  id("org.jetbrains.kotlin.kapt")
  id("org.jetbrains.kotlin.plugin.compose")
}

android {
  namespace = "ru.vladik.mobikiui"
  compileSdk = 35

  defaultConfig {
    applicationId = "ru.vladik.mobikiui"
    minSdk = 24
    targetSdk = 35
    versionCode = 7
    versionName = "1.6-pr4-map-leaflet"
  }

  buildTypes {
    release {
      isMinifyEnabled = false
      proguardFiles(
        getDefaultProguardFile("proguard-android-optimize.txt"),
        "proguard-rules.pro"
      )
    }
  }

  compileOptions {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
  }
  kotlinOptions {
    jvmTarget = "17"
  }

  buildFeatures {
    compose = true
    buildConfig = true
  }

  kapt {
    correctErrorTypes = true
  }

  packaging {
    resources {
      excludes += "/META-INF/{AL2.0,LGPL2.1}"
    }
  }
}

dependencies {
  val bom = platform("androidx.compose:compose-bom:2025.02.00")
  implementation(bom)
  androidTestImplementation(bom)

  implementation("androidx.core:core-ktx:1.15.0")
  implementation("com.google.android.material:material:1.12.0")
  implementation("androidx.activity:activity-compose:1.10.1")
  implementation("androidx.compose.ui:ui")
  implementation("androidx.compose.ui:ui-tooling-preview")
  implementation("androidx.compose.material3:material3:1.3.1")
  implementation("androidx.compose.material:material-icons-extended")
  implementation("androidx.navigation:navigation-compose:2.8.7")

  val room = "2.6.1"
  implementation("androidx.room:room-runtime:$room")
  implementation("androidx.room:room-ktx:$room")
  kapt("androidx.room:room-compiler:$room")

  implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.8.7")
  implementation("androidx.lifecycle:lifecycle-runtime-compose:2.8.7")
  implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.8.7")
  implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.8.1")

  debugImplementation("androidx.compose.ui:ui-tooling")
  debugImplementation("androidx.compose.ui:ui-test-manifest")

  implementation("com.squareup.retrofit2:retrofit:2.11.0")
  implementation("com.squareup.retrofit2:converter-gson:2.11.0")
  implementation("com.squareup.okhttp3:okhttp:4.12.0")
}

