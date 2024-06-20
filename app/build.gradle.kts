plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    id("kotlin-parcelize")
}

android {
    namespace = "com.example.psyapps"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.psyapps"
        minSdk = 24
        targetSdk = 34
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures{
        viewBinding = true
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.androidx.datastore.core.android)
    implementation(libs.androidx.navigation.fragment.ktx)
    implementation(libs.androidx.navigation.ui.ktx)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    //Glide
    implementation ("com.github.bumptech.glide:glide:4.16.0")
    //Retrofit
    implementation ("com.squareup.retrofit2:retrofit:2.10.0")
    implementation ("com.squareup.retrofit2:converter-gson:2.10.0")
    implementation ("com.squareup.okhttp3:logging-interceptor:5.0.0-alpha.11")
    //Lifecycle
    implementation ("androidx.lifecycle:lifecycle-viewmodel-ktx:2.8.2")
    implementation ("androidx.lifecycle:lifecycle-livedata-ktx:2.8.2")

    //Coroutine
    implementation ("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.3")
    implementation ("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.7.3")

    //CircleImageView
    implementation ("de.hdodenhof:circleimageview:3.1.0")

    //compose
    implementation("androidx.compose.ui:ui:1.6.8")
    implementation("androidx.compose.material3:material3:1.2.1")
    implementation("androidx.activity:activity-compose:1.9.0")

}