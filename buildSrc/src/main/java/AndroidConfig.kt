object AndroidConfig {
    const val appId = "com.dilip.qrventory"
    const val compileSdk = 34
    const val targetSdk = 34
    const val minSdk = 26
    const val versionCode = 1
    const val versionName = "1.0"

    // https://youtrack.jetbrains.com/issue/KT-66995/JvmTarget-and-JavaVersion-compatibility-for-easier-JVM-version-setup
    val JavaVersion = org.gradle.api.JavaVersion.VERSION_17
    val JvmTarget = org.jetbrains.kotlin.gradle.dsl.JvmTarget.JVM_17
}
