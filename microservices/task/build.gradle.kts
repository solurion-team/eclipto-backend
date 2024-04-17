plugins {
    `eclipto-microservice`
    `eclipto-openapi-generation`
}

dependencies {
    implementation(libs.database.postgresql)
    implementation(libs.database.h2)
    implementation("org.springframework.boot:spring-boot-starter-websocket:3.2.4")
    implementation("org.mapstruct:mapstruct:1.5.5.Final")
    implementation("org.mapstruct:mapstruct-processor:1.5.5.Final")
    annotationProcessor("org.mapstruct:mapstruct-processor:1.5.5.Final")


}

group = "com.solurion.eclipto.task"
version = "0.0.1-SNAPSHOT"

