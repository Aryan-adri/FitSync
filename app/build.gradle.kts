plugins {
    id("com.android.application")
    alias(libs.plugins.android.application) apply false
    id("org.jetbrains.kotlin.android") version "1.8.20"  // Make sure this version matches the Kotlin version you're using
    kotlin("android")
    kotlin("kapt")
}

}
android {
compileSdkVersion(33) // Use the latest compile SDK version you're targeting

defaultConfig {
applicationId = "com.example.fitsync" // Make sure the package name matches your app's package name
minSdkVersion(21) // Set the minimum SDK version you support
targetSdkVersion(33) // Set the target SDK version you support
versionCode = 1
versionName = "1.0"
testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
}

buildTypes {
getByName("release") {
isMinifyEnabled = false
proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
}
}
}

dependencies {
// AndroidX libraries
implementation("org.jetbrains.kotlin:kotlin-stdlib:1.8.20")
implementation("androidx.appcompat:appcompat:1.4.1")
implementation("androidx.core:core-ktx:1.7.0")
implementation("androidx.constraintlayout:constraintlayout:2.1.1")
implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.3.1")
implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.3.1")
implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.3.1")

// Material Design
implementation("com.google.android.material:material:1.5.0")

// Graph plotting library for BMI graph
implementation("com.github.PhilJay:MPAndroidChart:v3.1.0")

// Retrofit for network requests (if needed for fetching external data)
implementation("com.squareup.retrofit2:retrofit:2.9.0")
implementation("com.squareup.retrofit2:converter-gson:2.9.0")

// Gson for JSON parsing (if using Retrofit or other APIs)
implementation("com.google.code.gson:gson:2.8.8")

// Room for database (if you want to persist data like workout logs)
implementation("androidx.room:room-runtime:2.3.0")
kapt("androidx.room:room-compiler:2.3.0")

// Coroutines for background tasks (if needed for async operations)
implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.5.1")

// Testing dependencies
testImplementation("junit:junit:4.13.2")
androidTestImplementation("androidx.test.ext:junit:1.1.3")
androidTestImplementation("androidx.test.espresso:espresso-core:3.4.0")
}

// Enable Kotlin annotation processing
kapt{
correctErrorTypes = true
}
