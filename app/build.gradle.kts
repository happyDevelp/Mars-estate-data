plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
    id ("androidx.navigation.safeargs.kotlin")
}

android {
    namespace = "com.example.marsestatedata"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.marsestatedata"
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
        dataBinding = true
    }
}

dependencies {

    implementation("androidx.core:core-ktx:1.12.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.11.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")


    // ViewModel and LiveData
    // implementation "androidx.lifecycle:lifecycle-extensions:$version_lifecycle_extensions"
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.7.0")

    // Navigation
    implementation("androidx.navigation:navigation-fragment-ktx:2.7.6")
    implementation("androidx.navigation:navigation-ui-ktx:2.7.6")

    // Moshi
    implementation ("com.squareup.moshi:moshi:1.9.3")
    implementation ("com.squareup.moshi:moshi-kotlin:1.9.3")

    // Retrofit - Deprecated plugins - No more required
    // implementation "com.squareup.retrofit2:retrofit:$version_retrofit"
    // implementation "com.squareup.retrofit2:converter-scalars:$version_retrofit"

    // Retrofit with Moshi Converter
    implementation ("com.squareup.retrofit2:converter-moshi:2.9.0")

    // Coroutines - Deprecated - No more required
    // implementation "org.jetbrains.kotlinx:kotlinx-coroutines-core:$version_kotlin_coroutines"
    // implementation "org.jetbrains.kotlinx:kotlinx-coroutines-android:$version_kotlin_coroutines"

    // Retrofit Coroutines Support - Deprecated - No more required
    // implementation "com.jakewharton.retrofit:retrofit2-kotlin-coroutines-adapter:$version_retrofit_coroutines_adapter"

 /*   // Glide
    implementation ("com.github.bumptech.glide:glide:4.8.0")*/

    // RecyclerView
    implementation ("androidx.recyclerview:recyclerview:1.3.2")

    implementation("androidx.databinding:databinding-runtime:8.2.1")

}