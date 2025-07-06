plugins {
    kotlin("jvm") version "2.0.21"
    kotlin("plugin.spring") version "2.0.21"
    kotlin("plugin.serialization") version "2.0.21"
    id("io.gitlab.arturbosch.detekt") version "1.23.7"
    id("org.jetbrains.kotlinx.kover") version "0.9.1"
    id("org.jlleitschuh.gradle.ktlint") version "12.1.2"
}

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(21)
    }
}

repositories {
    mavenCentral()
    maven { url = uri("https://repo.spring.io/milestone") }
}

dependencies {
    // Spring Boot Platform BOM
    implementation(platform("org.springframework.boot:spring-boot-dependencies:3.5.3"))
    implementation(platform("org.springframework.ai:spring-ai-bom:1.0.0-M7"))
    // Spring Boot starters
    implementation("org.springframework.boot:spring-boot-starter-validation")
    // Apache Jackrabbit
    implementation("org.apache.jackrabbit:jackrabbit-core:2.22.0")
    implementation("org.apache.jackrabbit:jackrabbit-jcr2dav:2.22.0")
    // Kotlin coroutines
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-reactor")
    implementation("io.projectreactor.kotlin:reactor-kotlin-extensions")
    // Jackson Kotlin support
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    // Kotlinx Serialization
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.7.1")
    // Testing
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("io.projectreactor:reactor-test")
    testImplementation("io.mockk:mockk:1.13.13")
    testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test")
}

kotlin {
    compilerOptions {
        freeCompilerArgs.add("-Xjsr305=strict")
        jvmTarget.set(org.jetbrains.kotlin.gradle.dsl.JvmTarget.JVM_21)
    }
}

detekt {
    buildUponDefaultConfig = true
    config.setFrom(files("$projectDir/config/detekt/detekt.yml"))
}

kover {
    reports {
        filters {
            excludes {
                classes("*Application*", "*Configuration*")
            }
        }
    }
}
