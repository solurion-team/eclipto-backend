plugins {
    `eclipto-microservice`
}

dependencies {
    implementation(project(":common"))
    implementation(libs.database.postgresql)
    implementation(libs.database.h2)
}

group = "com.solurion.eclipto.project"
version = "0.0.1-SNAPSHOT"

