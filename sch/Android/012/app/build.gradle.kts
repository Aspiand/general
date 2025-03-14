plugins {
    alias(libs.plugins.android.application)
}

android {
    namespace = "id.my.aspian.l012"
    compileSdk = 35

    defaultConfig {
        applicationId = "id.my.aspian.l012"
        minSdk = 32
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
}

dependencies {
    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.activity)
    implementation(libs.constraintlayout)
    //
    implementation(libs.fragment)
    implementation(libs.glide)
    implementation(libs.media3.exoplayer)
    implementation(libs.media3.exoplayer.dash)
    implementation(libs.media3.ui)
    //
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)
}