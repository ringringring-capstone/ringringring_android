plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.jetbrainsKotlinAndroid)
    kotlin("kapt")
    id ("kotlin-parcelize")
    id("com.google.dagger.hilt.android")
    id("com.google.devtools.ksp")
}

android {
    namespace = "com.example.callphobia_overs"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.callphobia_overs"
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

    buildFeatures {
        viewBinding = true
        //noinspection DataBindingWithoutKapt
        dataBinding = true
    }

    kapt {
        correctErrorTypes = true
    }

}


dependencies {
    val glideVersion = "4.16.0"
    val fragmentVersion = "1.6.2"
    val CoroutineVersion = "1.3.9"
    val navVersion = "2.7.7"
    val retrofitVersion = "2.11.0"
    val okhttpVersion = "4.12.0"
    val okhttpLoggingVersion = "4.9.2"
    val annotationVersion = "1.7.1"
    val hiltVersion = "2.48"
    val lifecycleVersion = "2.7.0"
    val roomVersion = "2.6.1"
    val lottieVersion = "6.4.0"

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    //implementation("com.google.android.material:material:1.11.0")
    //Glide
    implementation("com.github.bumptech.glide:glide:$glideVersion")

    //Fragment
    implementation ("androidx.fragment:fragment-ktx:$fragmentVersion")

    //Coroutine
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:$CoroutineVersion")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:$CoroutineVersion")

    //room
    implementation("androidx.room:room-runtime:$roomVersion")
    //annotationProcessor("androidx.room:room-compiler:$roomVersion")
    ksp("androidx.room:room-compiler:$roomVersion")

    //Coroutine lifecycle
    implementation ("androidx.lifecycle:lifecycle-viewmodel-ktx:$lifecycleVersion")
    implementation ("androidx.lifecycle:lifecycle-runtime-ktx:$lifecycleVersion")

    //Navigation
    implementation ("androidx.navigation:navigation-fragment-ktx:$navVersion")
    implementation ("androidx.navigation:navigation-ui-ktx:$navVersion")

    //retrofit
    implementation ("com.squareup.retrofit2:retrofit:$retrofitVersion")
    implementation ("com.squareup.retrofit2:converter-gson:$retrofitVersion")

    //okhttp3
    implementation ("com.squareup.okhttp3:okhttp:$okhttpVersion")
    implementation ("com.squareup.okhttp3:logging-interceptor:$okhttpLoggingVersion")

    //annotation
    implementation ("androidx.annotation:annotation:$annotationVersion")

    //chart
    implementation ("com.github.PhilJay:MPAndroidChart:v3.1.0")

    //hlit
    implementation ("com.google.dagger:hilt-android:$hiltVersion")
    kapt("com.google.dagger:hilt-android-compiler:$hiltVersion")

    //lottie (벡터기반 애니메이션)
    implementation("com.airbnb.android:lottie:$lottieVersion")
}

