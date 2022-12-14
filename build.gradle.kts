plugins {
    id("org.jetbrains.kotlin.jvm") version "1.7.22"
    id("org.jetbrains.kotlin.plugin.serialization") version "1.7.22"
    id("org.jlleitschuh.gradle.ktlint") version "11.0.0"
    id("org.jlleitschuh.gradle.ktlint-idea") version "11.0.0"
    application
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.4.1")
    implementation(platform("org.jetbrains.kotlin:kotlin-bom"))
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    implementation("com.google.guava:guava:31.1-jre")
    implementation("io.github.cdimascio:dotenv-kotlin:6.4.0")
    implementation("io.vertx:vertx-core:4.3.6")
    implementation("io.vertx:vertx-lang-kotlin:4.3.6")
    implementation("io.vertx:vertx-web:4.3.6")
    implementation("org.litote.kmongo:kmongo:4.8.0")
}

application {
    mainClass.set("MainKt")
}
