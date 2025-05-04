plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android") version "1.8.20"  // Make sure this version matches the Kotlin version you're using
    kotlin("kapt") version "2.1.10"
}

android {
    namespace = "com.example.fitsync"
    compileSdk = 34 // Use the latest compile SDK version you're targeting

defaultConfig {
    applicationId = "com.example.fitsync" // Make sure the package name matches your app's package name
    minSdk = 21 // Set the minimum SDK version you support
    targetSdk = 35 // Set the target SDK version you support
    versionCode = 1
    versionName = "1.0"
    testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
}

    buildTypes {
    getByName("release") {
    isMinifyEnabled = false
    proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
    }

    compileOptions{
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions{
        jvmTarget = "1.8"
    }
}
}

dependencies {
    // AndroidX libraries
    implementation(libs.appcompat)
    implementation(libs.material)
    implementation("androidx.activity:activity:1.9.0")
    implementation("com.google.code.gson:gson:2.13.1")
    implementation("com.squareup.retrofit2:retrofit:2.11.0")
    implementation("com.squareup.retrofit2:converter-gson:2.11.0")
    implementation("com.squareup.okhttp3:okhttp:4.12.0")
    implementation("com.github.PhilJay:MPAndroidChart:v3.1.0")
    implementation("com.github.bumptech.glide:glide:4.15.1")
    implementation(libs.constraintlayout)
    annotationProcessor("com.github.bumptech.glide:compiler:4.15.1")
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)

    implementation("org.jetbrains.kotlin:kotlin-stdlib:2.1.10")


}

// Enable Kotlin annotation processing
kapt{
correctErrorTypes = true
}
