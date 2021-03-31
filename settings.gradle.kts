rootProject.name="micronaut-hexagonal-quickstart"

include("main", "domain")

// Input
include("rest", "kafka-consumer")
project(":rest").projectDir = file("input/rest")
project(":kafka-consumer").projectDir = file("input/kafka-consumer")

// Secondary
include("mysql")
project(":mysql").projectDir = file("output/mysql")