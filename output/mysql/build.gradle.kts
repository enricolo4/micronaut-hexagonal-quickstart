dependencies {
    implementation(project(":domain"))

    kapt("io.micronaut.data:micronaut-data-processor")

    implementation("io.micronaut.r2dbc:micronaut-r2dbc-core")
    implementation("io.micronaut.r2dbc:micronaut-data-r2dbc")
    implementation("io.micronaut.data:micronaut-data-hibernate-jpa")
    implementation("io.micronaut.flyway:micronaut-flyway")

    runtimeOnly("mysql:mysql-connector-java")
    runtimeOnly("dev.miku:r2dbc-mysql")

    kaptTest("io.micronaut.data:micronaut-data-processor")
}