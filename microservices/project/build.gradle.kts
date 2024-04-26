plugins {
    `eclipto-microservice`
}

dependencies {
    implementation(libs.database.postgresql)
    implementation(libs.database.h2)
    implementation(project(":common"))
}

group = "com.solurion.eclipto.project"
version = "0.0.1-SNAPSHOT"

