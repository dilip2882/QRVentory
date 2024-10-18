import com.diffplug.gradle.spotless.SpotlessExtension
import com.diffplug.gradle.spotless.SpotlessPlugin
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript {
    repositories {
        google()
        mavenCentral()
    }
}

plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.kotlin.android) apply false
    alias(libs.plugins.android.library) apply false
    alias(libs.plugins.compose.compiler) apply false
    alias(libs.plugins.hilt) apply false
    alias(libs.plugins.ksp) apply false
    alias(libs.plugins.kotlin.serialization) apply false
    // Spotless plugin
    alias(libs.plugins.spotless) apply false
}

val ktlintVersion = libs.versions.ktlint.get()

// Spotless configuration
subprojects {
    apply<SpotlessPlugin>()
    configure<SpotlessExtension> {

        kotlin {
            target("**/*.kt", "**/*.kts")
            targetExclude("**/build/**/*.kt")
            ktlint(ktlintVersion)
                .editorConfigOverride(
                    mapOf(
                        "ktlint_function_naming_ignore_when_annotated_with" to "Composable",
                        "ktlint_standard_class-signature" to "disabled",
                        "ktlint_standard_discouraged-comment-location" to "disabled",
                        "ktlint_standard_function-expression-body" to "disabled",
                        "ktlint_standard_function-signature" to "disabled",
                        "ktlint_standard_max-line-length" to "disabled",
                        "ktlint_standard_type-argument-comment" to "disabled",
                        "ktlint_standard_value-argument-comment" to "disabled",
                        "ktlint_standard_value-parameter-comment" to "disabled",
                    )
                )
            trimTrailingWhitespace()
            endWithNewline()
        }

        kotlinGradle {
            target("src/**/*.kt")
            ktlint(ktlintVersion)
        }

        afterEvaluate {
            tasks.withType<KotlinCompile>() {
                finalizedBy("spotlessApply")
            }
        }

    }
}
