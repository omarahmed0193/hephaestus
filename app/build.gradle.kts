plugins {
    id("com.android.application")
    id("androidx.navigation.safeargs")
    kotlin("android")
    kotlin("android.extensions")
    kotlin("kapt")
}


android {
    compileSdkVersion(29)
    buildToolsVersion = "29.0.2"
    defaultConfig {
        applicationId = "com.afterapps.hephaestus"
        minSdkVersion(22)
        targetSdkVersion(29)
        versionCode = 1
        versionName = "1.0"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    androidExtensions {
        isExperimental = true
    }
    dataBinding {
        isEnabled = true
    }
}

dependencies {
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))

    //Kotlin
    implementation(Libs.kotlin_stdlib_jdk7)

    //Androidx Core
    implementation(Libs.core_ktx)

    // AndroidX AppCompat
    implementation(Libs.appcompat)

    // AndroidX Activity
    implementation(Libs.activity_ktx)

    // AndroidX Fragment
    implementation(Libs.fragment_ktx)

    //Constraint layout
    implementation(Libs.constraintlayout)

    //Recycler View
    implementation(Libs.recyclerview)

    //Material Components
    implementation(Libs.material)

    //Koin
    implementation (Libs.koin_androidx_viewmodel)

    //Lifecycle Extensions
    implementation (Libs.lifecycle_extensions)

    //Navigation
    implementation (Libs.navigation_fragment_ktx)
    implementation (Libs.navigation_ui_ktx)

    //Paging
    implementation (Libs.paging_runtime_ktx)

    //Room
    implementation(Libs.room_ktx)
    implementation (Libs.room_runtime)
    kapt(Libs.room_compiler)

    //Work Manager
    implementation (Libs.work_runtime_ktx)

    // Moshi
    implementation(Libs.moshi_kotlin)

    // Retrofit with Moshi Converter and OkHtp Interceptor
    implementation(Libs.retrofit)
    implementation(Libs.converter_moshi)
    implementation(Libs.logging_interceptor)

    // Coroutines
    implementation(Libs.kotlinx_coroutines_core)
    implementation(Libs.kotlinx_coroutines_android)

    // Retrofit Coroutines Support
    implementation(Libs.retrofit2_kotlin_coroutines_adapter)

    // Glide
    implementation(Libs.glide)

    // LiveEvent
    implementation(Libs.liveevent)

    //Facebook Shimmer
    implementation(Libs.shimmer)

    //Testing
    testImplementation("junit:junit:4.12")
    androidTestImplementation("androidx.test.ext:junit:1.1.1")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.2.0")
}
