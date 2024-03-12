plugins {
    `kotlin-dsl`
}

repositories {
    mavenCentral()
}

dependencies {
    implementation(gradleApi())
    implementation(files(libs.javaClass.superclass.protectionDomain.codeSource.location))
    implementation(libs.kotlin.gradlePlugin)
}

gradlePlugin {
    plugins {
        register("ecliptoMicroservicePlugin") {
            id = libs.plugins.eclipto.microservice.get().pluginId
            implementationClass = "project.plugin.EcliptoMicroservicePlugin"
        }
    }
}