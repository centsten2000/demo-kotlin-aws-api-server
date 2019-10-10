import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.3.50"
}

group = "demo.kotlin.aws.api.server"
version = "1.0"

repositories {
    jcenter()
}

dependencies {
    // Use the Kotlin JDK 8 standard library.
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    implementation("org.jetbrains.kotlin:kotlin-reflect")

    // Use AWS suite.
    implementation("com.amazonaws:aws-lambda-java-core:1.2.0")
    implementation("com.amazonaws:aws-java-sdk-lambda:1.11.+")

    // Use Logging.
    implementation("io.github.microutils:kotlin-logging:1.7.6")
    implementation("ch.qos.logback:logback-core:1.2.3")
    implementation("ch.qos.logback:logback-classic:1.2.3")

    // Use JSON parser.
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin:2.9.+")

    // Test related.
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.3.1")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.3.1")
    testImplementation("org.jetbrains.kotlin:kotlin-test")
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit")
    testImplementation("io.mockk:mockk:1.9.3")
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}

object Constants {
    const val appName = "demo-api-server"
    const val appVersion = "latest"
}

tasks.withType<Jar> {
    archiveBaseName.set("demo-api-server")

    from(configurations.compileClasspath.get().map {
        if (it.isDirectory) it else zipTree(it)
    })
}
