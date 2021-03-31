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

dependencies {
    implementation(project(":domain"))
    implementation(project(":rest"))
    implementation(project(":kafka-consumer"))
    implementation(project(":mysql"))
}