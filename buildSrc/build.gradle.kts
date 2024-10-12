plugins {
    `kotlin-dsl`
}

dependencies {
    compileOnly(libs.gradle.plugin)
    compileOnly(libs.kotlin.plugin)
}

repositories {
    mavenCentral()
    google()
}

//val compileKotlin: KotlinCompile by tasks
//compileKotlin.kotlinOptions {
//    jvmTarget = "1.8"
//}
