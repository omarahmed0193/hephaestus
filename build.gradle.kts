buildscript {
    repositories {
        google()
        jcenter()
    }
    dependencies {
        classpath ("com.android.tools.build:gradle:3.5.3")
        classpath ("org.jetbrains.kotlin:kotlin-gradle-plugin:1.3.61")
        classpath ("androidx.navigation:navigation-safe-args-gradle-plugin:2.1.0")
    }
}

plugins {
    buildSrcVersions
}

allprojects {
    repositories {
        google()
        jcenter()
        maven(url = "https://jitpack.io")
    }
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}
