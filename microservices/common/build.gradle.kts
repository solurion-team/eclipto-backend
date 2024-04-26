plugins {
    `eclipto-microservice`
    `eclipto-openapi-generation`
}

group = "com.solurion.eclipto.common"
version = "0.0.1-SNAPSHOT"

dependencies {
    implementation(libs.jakarta.validation.api)
    implementation(libs.jackson.datatype.jsr310)
    implementation(libs.bundles.jwt)
    implementation(libs.bundles.resourceSecurity)
}
