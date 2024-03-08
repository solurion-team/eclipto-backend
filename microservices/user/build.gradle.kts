plugins {
    `eclipto-microservice`
}

dependencies {
    implementation(libs.database.postgresql)
    implementation(libs.database.h2)
}

group = "com.solurion.eclipto.user"
version = "0.0.1-SNAPSHOT"

