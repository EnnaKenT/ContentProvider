const val kotlinVersion = "1.3.40"

object BuildPlugins {

    object Versions {
        const val buildToolsVersion = "3.4.1"
    }

    const val androidGradlePlugin = "com.android.tools.build:gradle:${Versions.buildToolsVersion}"
    const val kotlinGradlePlugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlinVersion"
    const val androidApplication = "com.android.application"
    const val kotlinAndroid = "kotlin-android"
    const val kotlinAndroidExtensions = "kotlin-android-extensions"
    const val kotlinKapt = "kotlin-kapt"

}

object AndroidSdk {
    const val min = 24
    const val compile = 29
    const val target = compile
}

object ContentProvider {
    const val name = "PROVIDER_AUTHORITY"
    const val value = "com.example.contentprovider.provider"
}

object Libraries {
    private object Versions {
        const val jetpack = "1.1.0-beta01"
        const val constraintLayout = "2.0.0-beta2"
        const val ktx = "1.2.0-alpha02"
        const val coroutines = "1.1.1"
        const val roomVersion = "2.1.0"
        const val materialVersion = "1.1.0-alpha07"
    }

    const val kotlinStdLib = "org.jetbrains.kotlin:kotlin-stdlib-jdk8:$kotlinVersion"
    const val appCompat = "androidx.appcompat:appcompat:${Versions.jetpack}"
    const val constraintLayout = "androidx.constraintlayout:constraintlayout:${Versions.constraintLayout}"
    const val ktxCore = "androidx.core:core-ktx:${Versions.ktx}"
    const val coroutinesCore = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.coroutines}"

    const val material = "com.google.android.material:material:${Versions.materialVersion}"

    const val roomKtx = "androidx.room:room-ktx:${Versions.roomVersion}"
    const val roomRuntime = "androidx.room:room-runtime:${Versions.roomVersion}"
    const val roomCompiler = "androidx.room:room-compiler:${Versions.roomVersion}"
}

object TestLibraries {
    private object Versions {
        const val junit4 = "4.13-beta-2"
        const val testRunner = "1.2.0"
        const val espresso = "3.2.0"
    }

    const val junit4 = "junit:junit:${Versions.junit4}"
    const val testRunner = "androidx.test:runner:${Versions.testRunner}"
    const val espresso = "androidx.test.espresso:espresso-core:${Versions.espresso}"
}

object FileTree {
    val fileTree = mapOf("dir" to "libs", "include" to listOf("*.jar"))
}