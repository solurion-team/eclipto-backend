plugins {
    `eclipto-microservice`
}

dependencies {
    implementation(libs.database.postgresql)
    implementation(libs.database.h2)
}

group = "com.solurion.eclipto.task"
version = "0.0.1-SNAPSHOT"

