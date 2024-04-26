plugins {
    `eclipto-microservice`
    `eclipto-openapi-generation`
}

dependencies {
    implementation(project(":common"))
    implementation(libs.database.postgresql)
    implementation(libs.database.h2)
    implementation(libs.bundles.jwt)
    implementation(libs.bundles.resourceSecurity)
}

group = "com.solurion.eclipto.user"
version = "0.0.1-SNAPSHOT"

