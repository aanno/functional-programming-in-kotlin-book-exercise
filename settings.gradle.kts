pluginManagement {
    val mavenUrl1: String by settings
    val mavenUrl2: String  by settings

    repositories {
        // gradlePluginPortal()
        maven {
            url = uri(mavenUrl1)
        }
        maven {
            url = uri(mavenUrl2)
        }
    }

    plugins {
        id("org.jetbrains.kotlin.jvm") version "1.9.20"
    }
}

rootProject.name = "functional-programming-in-kotlin-book-exercises"
