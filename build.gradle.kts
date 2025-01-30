// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.kotlin.android) apply false
    alias(libs.plugins.kotlin.compose) apply false
    kotlin("jvm") version "2.1.0" apply false
    id("io.ktor.plugin") version "2.3.5" apply false
    id("org.jetbrains.kotlin.plugin.serialization") version "2.1.0" apply false
}