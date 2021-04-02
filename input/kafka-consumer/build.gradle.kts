val kafkaJsonSerializer = project.properties["kafkaJsonSerializer"]

dependencies {
    implementation(project(":domain"))

    implementation("io.micronaut.kafka:micronaut-kafka")
    implementation("io.confluent:kafka-json-serializer:${kafkaJsonSerializer}")
    implementation("io.confluent:kafka-json-schema-serializer:${kafkaJsonSerializer}")
}