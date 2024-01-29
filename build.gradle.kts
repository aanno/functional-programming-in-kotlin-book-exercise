import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

group = "org.example"
version = "1.0-SNAPSHOT"
description = "functional-programming-in-kotlin-book-exercises"
java.sourceCompatibility = JavaVersion.VERSION_17

plugins {
    java
    idea
    id("org.jetbrains.kotlin.jvm")
}

val mavenUrl1 by properties
val mavenUrl2 by properties

repositories {
    // mavenLocal()
    // mavenCentral()
    maven {
        url = uri(mavenUrl1!!)
    }
    maven {
        url = uri(mavenUrl2!!)
    }
}

val arrowVersion = "0.11.0"
val junitVersion = "5.8.2"
val kotestVersion = "5.1.0"

dependencies {
    implementation("io.arrow-kt:arrow-core:$arrowVersion")
    implementation("io.arrow-kt:arrow-core-data:$arrowVersion")
    implementation("io.arrow-kt:arrow-mtl:$arrowVersion")
    implementation("io.arrow-kt:arrow-mtl-data:$arrowVersion")
    implementation("io.arrow-kt:arrow-syntax:$arrowVersion")
    implementation("io.arrow-kt:arrow-fx:$arrowVersion")
    testImplementation("org.junit.jupiter:junit-jupiter-api:$junitVersion")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:$junitVersion")
    testImplementation("org.junit.jupiter:junit-jupiter-params:$junitVersion")
    testImplementation("io.kotest:kotest-runner-junit5-jvm:$kotestVersion")
    testImplementation("io.kotest:kotest-assertions-core-jvm:$kotestVersion")
    testImplementation("io.kotest:kotest-property-jvm:$kotestVersion")
}

tasks.withType<JavaCompile> {
    options.encoding = "UTF-8"
}

tasks.withType<KotlinCompile>().configureEach {
    kotlinOptions {
        jvmTarget = "17"
    }
}

tasks {
    wrapper {
        gradleVersion = "8.5"
        distributionType = Wrapper.DistributionType.ALL
    }

    test {
        useJUnitPlatform()

        testLogging {
            events("passed", "skipped", "failed")
        }

        reports {
            html.required.set(true)
        }
    }
}
