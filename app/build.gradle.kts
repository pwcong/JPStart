import java.util.Properties

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
}

fun getLocalProperty(propertyName: String): String? {
    val propertiesFile = rootProject.file("local.properties")
    if (propertiesFile.exists()) {
        val properties = Properties().apply {
            load(propertiesFile.inputStream())
        }
        return properties.getProperty(propertyName)
    }
    return null
}

android {
    namespace = "com.github.pwcong.jpstart"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.github.pwcong.jpstart"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }

        buildConfigField("String", "ALIYUN_API_KEY", "\"${getLocalProperty("aliyun_api_key")}\"")
        buildConfigField(
            "String",
            "ALIYUN_API_SECRET",
            "\"${getLocalProperty("aliyun_api_secret")}\""
        )

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
        compose = true
        buildConfig = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    viewBinding {
        enable = true
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)

    implementation(libs.material)

    implementation(libs.androidx.appcompat)
    implementation(libs.androidx.recyclerview)

    // Util
    implementation(libs.rxandroid)
    implementation(libs.rxjava)
    implementation(libs.sqliteassethelper)
    implementation(libs.utilcode)
    implementation(libs.glide)
    implementation(libs.retrofit)
    implementation(libs.converter.gson)
    implementation(libs.adapter.rxjava)
    implementation(libs.alibabacloud.alimt20181012)

    // UI Component
    implementation(libs.circleindicator)
    implementation(libs.radiobuttonview)
    implementation(libs.swipecards)
    implementation(libs.floatingactionbutton)
    implementation(libs.textdrawable)
    implementation(libs.photoview)
}