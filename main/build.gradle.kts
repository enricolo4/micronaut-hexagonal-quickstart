plugins {
    application
    id("io.micronaut.application") version "1.4.2"
}

kapt {
    arguments {
        arg("micronaut.processing.incremental", true)
        arg("micronaut.processing.annotations", "com.quickstart.*,io.quickstart.*")
    }
}

application {
    mainClass.set("com.quickstart.Application")

    applicationDefaultJvmArgs = listOf(
        "-server",
        "-XX:+UseNUMA",
        "-XX:+UseParallelGC",
        "-Duser.timezone=America/Sao_Paulo"
    )
}

micronaut {
    version("2.4.2-SNAPSHOT")
    runtime("netty")
    testRuntime("kotest")

    processing {
        incremental(true)
        annotations("com.quickstart.*")
    }
}

tasks {
    jar {
        archiveBaseName.set("micronaut-hexagonal-quickstart")
        archiveVersion.set("")

        manifest {
            attributes(mapOf("Main-Class" to application.mainClass))
        }

        from(
            Callable {
                duplicatesStrategy = DuplicatesStrategy.INCLUDE
                isZip64 = true
                configurations["runtimeClasspath"].map { if (it.isDirectory) it else zipTree(it) }
            }
        )
    }
}

val kafkaJsonSerializer = project.properties["kafkaJsonSerializer"]
val micronautKafka = project.properties["micronautKafka"]
val micronautVersion = project.properties["micronautVersion"]

val inputProjects = listOf(":rest")
val outputProjects = listOf(":mysql", ":kafka-producer")
val projects = listOf(":domain") + inputProjects + outputProjects

dependencies {
    projects.map { projects -> project(projects) }
        .forEach { projectDependency -> implementation(projectDependency) }

    kapt("io.micronaut:micronaut-bom:${micronautVersion}")
    implementation(platform("io.micronaut:micronaut-bom:${micronautVersion}"))

    kaptTest("io.micronaut:micronaut-bom:${micronautVersion}")

    testImplementation("io.micronaut.kafka:micronaut-kafka")
    testImplementation("io.confluent:kafka-json-serializer:${kafkaJsonSerializer}")
    testImplementation("io.confluent:kafka-json-schema-serializer:${kafkaJsonSerializer}")
}