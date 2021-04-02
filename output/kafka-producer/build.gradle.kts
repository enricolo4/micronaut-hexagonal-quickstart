val kafkaJsonSerializer = project.properties["kafkaJsonSerializer"]
val micronautKafka = project.properties["micronautKafka"]

dependencies {
    implementation(project(":domain"))

    implementation("io.micronaut.kafka:micronaut-kafka")
    implementation("io.confluent:kafka-json-serializer:${kafkaJsonSerializer}")
    implementation("io.confluent:kafka-json-schema-serializer:${kafkaJsonSerializer}")
}