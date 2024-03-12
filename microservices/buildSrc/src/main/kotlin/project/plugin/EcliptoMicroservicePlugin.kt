package project.plugin

import project.extensions.*
import groovy.lang.Closure
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.*
import org.jetbrains.kotlin.gradle.utils.extendsFrom
import org.gradle.api.artifacts.Configuration
import org.gradle.api.tasks.testing.Test
import org.gradle.jvm.toolchain.JavaLanguageVersion
import project.extensions.annotationProcessor
import project.extensions.implementation
import project.extensions.libs
import project.extensions.testImplementation

class EcliptoMicroservicePlugin : Plugin<Project> {
    override fun apply(target: Project) = with(target) {
        with(pluginManager) {
            this.apply("org.gradle.java")
            this.apply(libs.plugins.spring.boot.get().pluginId)
            this.apply(libs.plugins.spring.dependency.management.get().pluginId)
        }

        configurations.named<Configuration>("compileOnly")
            .extendsFrom(configurations.named<Configuration>("annotationProcessor"))

        dependencies {
            implementation(libs.spring.boot.starter.web)
            implementation(libs.spring.boot.starter.actuator)
            implementation(libs.spring.boot.starter.data.jpa)
            implementation(libs.spring.boot.starter.websocket)
            implementation(libs.lombok)
            annotationProcessor(libs.spring.boot.configuration.processor)
            annotationProcessor(libs.lombok)
            testImplementation(libs.spring.boot.starter.test)
        }

        tasks.withType<Test> {
            useJUnitPlatform()
        }

        (this as org.gradle.api.plugins.ExtensionAware).extensions.configure<org.gradle.api.plugins.JavaPluginExtension>("java") {
            toolchain {
                languageVersion = JavaLanguageVersion.of(21)
            }
        }

        Unit
    }
}