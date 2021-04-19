val kotlinVersion = project.properties["kotlinVersion"]
val micronautVersion = project.properties["micronautVersion"]

buildscript {
    repositories {
        mavenCentral()
        jcenter()
        gradlePluginPortal()
        maven("https://s01.oss.sonatype.org/content/repositories/snapshots/")
        maven("https://jcenter.bintray.com")
        maven("https://packages.confluent.io/maven/")
        maven("https://jitpack.io")
    }

    dependencies {
        classpath( kotlin("gradle-plugin:1.4.30"))
    }
}

plugins {
    kotlin("jvm") version "1.4.30"
    kotlin("kapt") version "1.4.30"
    id("org.jetbrains.kotlin.plugin.allopen") version "1.4.30"
}

allprojects {
    apply(plugin = "org.jetbrains.kotlin.jvm")
    apply(plugin = "org.jetbrains.kotlin.kapt")
    apply(plugin = "org.jetbrains.kotlin.plugin.allopen")

    repositories {
        mavenCentral()
        jcenter()
        gradlePluginPortal()
        maven("https://s01.oss.sonatype.org/content/repositories/snapshots/")
        maven("https://jcenter.bintray.com")
        maven("https://packages.confluent.io/maven/")
        maven("https://jitpack.io")
    }

    dependencies {
        kapt("io.micronaut:micronaut-bom:${micronautVersion}")
        kapt("io.micronaut:micronaut-graal")
        kapt("io.micronaut:micronaut-validation")
        kapt("io.micronaut:micronaut-inject-java")
    }
}

subprojects {
    group = "com.quickstart"
    version = "0.1"

    sourceSets {
        getByName("main").java.srcDirs("src/main/kotlin")
    }

    sourceSets.main {
        java.srcDir("src/main/kotlin")
    }

    repositories {
        mavenCentral()
        maven("https://s01.oss.sonatype.org/content/repositories/snapshots/")
    }

    dependencies {
        kapt("io.micronaut:micronaut-bom:${micronautVersion}")
        kapt("io.micronaut:micronaut-graal")
        kapt("io.micronaut:micronaut-validation")
        kapt("io.micronaut:micronaut-inject-java")

        implementation(platform("io.micronaut:micronaut-bom:${micronautVersion}"))

        implementation("org.jetbrains.kotlin:kotlin-reflect")
        implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
        implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core")
        implementation("org.jetbrains.kotlinx:kotlinx-coroutines-reactor")
        implementation("io.projectreactor.kotlin:reactor-kotlin-extensions:1.0.2.RELEASE")

        implementation("io.micronaut.reactor:micronaut-reactor")
        implementation("io.micronaut.kotlin:micronaut-kotlin-runtime")
        implementation("io.micronaut:micronaut-inject")
        implementation("io.micronaut:micronaut-validation")
        implementation("io.micronaut.kotlin:micronaut-kotlin-extension-functions")

        implementation("javax.annotation:javax.annotation-api")

        runtimeOnly("ch.qos.logback:logback-classic")
        runtimeOnly("com.fasterxml.jackson.module:jackson-module-kotlin")

        kaptTest("io.micronaut:micronaut-bom:${micronautVersion}")
        kaptTest("io.micronaut:micronaut-graal")
        kaptTest("io.micronaut:micronaut-validation")
        kaptTest("io.micronaut:micronaut-inject-java")

        testImplementation(platform("io.micronaut:micronaut-bom:${micronautVersion}"))

        testImplementation("io.micronaut.test:micronaut-test-kotest")
        testImplementation("io.mockk:mockk:1.10.5")
        testImplementation("io.kotest:kotest-runner-junit5-jvm:4.3.0")
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
        test {
            useJUnitPlatform()
        }
    }
}