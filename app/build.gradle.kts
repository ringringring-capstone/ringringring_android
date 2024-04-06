plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.jetbrainsKotlinAndroid)
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
    }
}

dependencies {
    val glideVersion = "4.16.0"
    val fragmentVersion = "1.6.2"
    val CoroutineVersion = "1.3.9"
    val navVersion = "2.7.7"


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

    //Navigation
    implementation("androidx.navigation:navigation-fragment-ktx:$navVersion")
    implementation("androidx.navigation:navigation-ui-ktx:$navVersion")
}