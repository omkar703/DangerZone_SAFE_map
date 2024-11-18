plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    alias(libs.plugins.google.gms.google.services)
}

android {
    namespace = "np.com.bimalkafle.firebaseauthdemoapp"
    compileSdk = 35

    defaultConfig {
        applicationId = "np.com.bimalkafle.firebaseauthdemoapp"
        minSdk = 26
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
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
        kotlinCompilerExtensionVersion = "1.5.1"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    buildFeatures {
        compose = true
    }

}

dependencies {

    implementation ("com.google.maps.android:android-maps-utils:2.2.5")

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    implementation(libs.androidx.navigation.compose)
    implementation(libs.firebase.auth)
    implementation(libs.androidx.runtime.livedata)
    implementation(libs.places)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)


    val nav_version = "2.8.3"
        implementation ("com.google.maps.android:maps-compose:6.2.1")  // Google Maps Compose
        implementation ("com.google.android.gms:play-services-maps:18.1.0")  // Google Maps API
        implementation ("com.google.android.gms:play-services-location:21.0.1")  // Location Services
        implementation ("androidx.navigation:navigation-compose:$nav_version" ) // Navigation Compose
        // Kotlin Standard Library
        implementation ("androidx.lifecycle:lifecycle-runtime-ktx:2.6.1")  // Lifecycle Components (optional but useful)

    implementation ("com.squareup.moshi:moshi-kotlin:1.12.0")
    implementation("com.squareup.okhttp3:okhttp:4.9.0")  // For making HTTP requests
    implementation("com.google.android.gms:play-services-location:21.0.1")
    implementation("com.squareup.moshi:moshi:1.12.0")  // For JSON parsing
    implementation ("androidx.compose.ui:ui:1.4.0")
    implementation ("androidx.compose.material3:material3:1.0.0")
    implementation ("androidx.navigation:navigation-compose:2.6.0")

    // Retrofit for making network requests
    implementation ("com.squareup.retrofit2:retrofit:2.9.0")
    implementation ("com.squareup.retrofit2:converter-gson:2.9.0")

    // OkHttp for network requests (optional but useful for debugging)
    implementation ("com.squareup.okhttp3:okhttp:4.9.0")
    implementation ("com.squareup.okhttp3:logging-interceptor:4.9.0")

    implementation("com.google.accompanist:accompanist-permissions:0.24.13-rc")
    implementation ("com.google.maps.android:maps-compose:2.0.0")
    implementation ("com.google.android.gms:play-services-maps:17.0.1")
    implementation ("com.google.accompanist:accompanist-permissions:0.24.13-rc")
    implementation ("com.squareup.okhttp3:okhttp:4.9.0")
    implementation ("com.squareup.moshi:moshi:1.11.0")
    implementation ("com.squareup.moshi:moshi-kotlin:1.11.0")
    implementation ("androidx.core:core-ktx:1.9.0" )// For NotificationManager
    implementation ("androidx.compose.material3:material3:1.1.0" )// For Material3
    implementation ("com.google.android.gms:play-services-location:21.0.1") // For location services
    implementation ("com.google.maps.android:maps-compose:2.2.0") // For Google Maps in Jetpack Compose
    implementation ("com.google.accompanist:accompanist-permissions:0.30.0")// For permission handling
    implementation ("com.google.accompanist:accompanist-permissions:0.30.0")




}