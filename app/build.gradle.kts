plugins {
    id("com.android.application")
    id("kotlin-android")
    id("kotlin-android-extensions")
    id("kotlin-kapt")
}

android {
    compileSdkVersion(AndroidSdk.compile)
    defaultConfig {
        applicationId = "com.example.contentprovider"
        minSdkVersion(AndroidSdk.min)
        targetSdkVersion(AndroidSdk.target)
        versionCode = 1
        versionName = "1.0"
        testInstrumentationRunner = AndroidSdk.androidJUnitRunner

        kapt.arguments {
            arg("room.schemaLocation", "$projectDir/schemas")
        }

        resValue("string", ContentProvider.name, ContentProvider.value)
        buildConfigField("String", ContentProvider.name, "\"${ContentProvider.value}\"")
    }
    buildTypes.getByName("release") {
        isMinifyEnabled = false
        proguardFiles(
            getDefaultProguardFile("proguard-android-optimize.txt"),
            "proguard-rules.pro"
        )
    }
}

androidExtensions.isExperimental = true

dependencies {
    implementation(fileTree(FileTree.fileTree))
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8:$kotlinVersion")
    implementation("androidx.appcompat:appcompat:1.2.0")
    implementation("androidx.core:core-ktx:1.6.0-alpha03")
    implementation("com.google.android.material:material:1.4.0-alpha02")
    implementation("androidx.constraintlayout:constraintlayout:2.1.0-beta02")

    testImplementation("junit:junit:4.13-beta-2")
    androidTestImplementation("androidx.test:runner:1.3.0")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.3.0")

    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.4.1")

    implementation("androidx.room:room-ktx:$roomVersion")
    implementation("androidx.room:room-runtime:$roomVersion")
    kapt("androidx.room:room-compiler:$roomVersion")
}