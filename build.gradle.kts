import org.jetbrains.kotlin.gradle.dsl.Coroutines

val kotlinVersion= project.properties["kotlinVersion"]
val micronautVersion = project.properties["micronautVersion"]

plugins {
    kotlin("jvm") version "1.4.30"
    kotlin("kapt") version "1.4.30"
    id("org.jetbrains.kotlin.plugin.allopen") version "1.4.30"
    id("io.spring.dependency-management") version "1.0.11.RELEASE"
}

allprojects {
    apply (plugin = "org.jetbrains.kotlin.jvm")
    apply (plugin = "org.jetbrains.kotlin.kapt")
    apply (plugin = "org.jetbrains.kotlin.plugin.allopen")
    apply (plugin = "io.spring.dependency-management")

    repositories {
        mavenCentral()
        maven("https://s01.oss.sonatype.org/content/repositories/snapshots/")
    }
}

subprojects {
    group = "com.quickstart"
    version = "0.1"

    sourceSets {
        getByName("main").java.srcDirs("src/main/kotlin")
    }

    sourceSets {
        main {
            java {
                srcDir("src/main/kotlin")
            }
        }
    }

    repositories {
        mavenCentral()
        maven("https://s01.oss.sonatype.org/content/repositories/snapshots/")
    }

    dependencies {
        kapt("io.micronaut.data:micronaut-data-processor")

        kapt("io.micronaut:micronaut-inject-java")
        kaptTest("io.micronaut:micronaut-inject-java")

        implementation("org.jetbrains.kotlin:kotlin-reflect")
        implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
        implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core")
        implementation("org.jetbrains.kotlinx:kotlinx-coroutines-reactor")

        implementation("io.micronaut.reactor:micronaut-reactor")
        implementation("io.micronaut:micronaut-runtime")
        implementation("io.micronaut.flyway:micronaut-flyway")
        implementation("io.micronaut.kotlin:micronaut-kotlin-runtime")
        implementation("io.micronaut:micronaut-inject")
        implementation("io.micronaut:micronaut-validation")
        implementation("io.micronaut.kotlin:micronaut-kotlin-extension-functions")

        implementation("javax.annotation:javax.annotation-api")

        runtimeOnly("ch.qos.logback:logback-classic")
        runtimeOnly("com.fasterxml.jackson.module:jackson-module-kotlin")

        dependencyManagement {
            imports {
                mavenBom("io.micronaut:micronaut-bom:${micronautVersion}")
            }
        }
    }

    kotlin {
        experimental.coroutines = Coroutines.ENABLE
    }

    java {
        sourceCompatibility = JavaVersion.toVersion("11")
    }

    tasks {
        compileKotlin {
            kotlinOptions {
                jvmTarget = "11"
            }
        }
        compileTestKotlin {
            kotlinOptions {
                jvmTarget = "11"
            }
        }
    }
}