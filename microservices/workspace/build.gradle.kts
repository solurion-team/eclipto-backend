plugins {
    `eclipto-microservice`
}

dependencies {
    implementation(libs.database.postgresql)
    implementation(libs.database.h2)
    implementation(project(":common"))
}

group = "com.solurion.eclipto.workspace"
version = "0.0.1-SNAPSHOT"

