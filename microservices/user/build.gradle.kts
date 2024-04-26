plugins {
    `eclipto-microservice`
    `eclipto-openapi-generation`
}

dependencies {
    implementation(libs.database.postgresql)
    implementation(libs.database.h2)
    implementation(project(":common"))
    implementation("org.springframework.boot:spring-boot-starter-oauth2-resource-server")
    implementation("io.jsonwebtoken:jjwt:0.2")
//    implementation("org.springframework.boot:spring-boot-starter-oauth2-client")
//    implementation("org.springframework.boot:spring-boot-starter-oauth2-resource-server")
    implementation("org.springframework.boot:spring-boot-starter-security")
    implementation("org.springframework.security:spring-security-config")
    testImplementation("org.springframework.security:spring-security-test")
    implementation("com.google.crypto.tink:tink:1.13.0")

    implementation("org.mapstruct:mapstruct:1.5.5.Final")
    implementation("org.mapstruct:mapstruct-processor:1.5.5.Final")
    annotationProcessor("org.mapstruct:mapstruct-processor:1.5.5.Final")
    // https://mvnrepository.com/artifact/com.nimbusds/nimbus-jose-jwt
    implementation("com.nimbusds:nimbus-jose-jwt:9.37.3")
}

group = "com.solurion.eclipto.user"
version = "0.0.1-SNAPSHOT"

