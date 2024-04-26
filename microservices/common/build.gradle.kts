plugins {
    `eclipto-microservice`
    `eclipto-openapi-generation`
}

group = "com.solurion.eclipto.common"
version = "0.0.1-SNAPSHOT"

dependencies {
    implementation("jakarta.validation:jakarta.validation-api:3.0.2")
    implementation("com.nimbusds:nimbus-jose-jwt:9.37.3")
    implementation("io.jsonwebtoken:jjwt:0.2")
    implementation("com.google.crypto.tink:tink:1.13.0")
    implementation("com.fasterxml.jackson.datatype:jackson-datatype-jsr310:2.17.0")
    implementation("org.springframework.boot:spring-boot-starter-oauth2-resource-server")
    implementation("org.springframework.boot:spring-boot-starter-security")
}
