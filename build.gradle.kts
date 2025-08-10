import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.kotlin.jvm)
    alias(libs.plugins.spring.boot) apply false
    alias(libs.plugins.spring.dependency.management) apply false
    alias(libs.plugins.kotlin.spring) apply false
    alias(libs.plugins.kotlin.jpa) apply false
    `java-test-fixtures`
    id("io.kotest") version "0.4.11"
}

allprojects {
    group = "com.musinsa.assignment"
    version = "0.0.1-SNAPSHOT"

    repositories {
        mavenCentral()
    }
}
java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(17)
    }
}
subprojects {
    apply(plugin = "kotlin")
    apply(plugin = "kotlin-spring")
    apply(plugin = "org.springframework.boot")
    apply(plugin = "io.spring.dependency-management")
    apply(plugin = "java-test-fixtures")
    kotlin {
        compilerOptions {
            freeCompilerArgs.addAll("-Xjsr305=strict")
            jvmTarget.set(JvmTarget.JVM_17)
        }
        jvmToolchain(17)
    }

    dependencies {
        implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core")
    }
    tasks.test {
        useJUnitPlatform()
    }
}
