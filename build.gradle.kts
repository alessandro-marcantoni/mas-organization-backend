plugins {
    id("org.jetbrains.kotlin.jvm") version "2.3.10"
    id("org.jetbrains.kotlin.plugin.serialization") version "2.3.0"
    id("org.jlleitschuh.gradle.ktlint") version "11.1.0"
    id("org.jlleitschuh.gradle.ktlint-idea") version "11.1.0"
    application
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.10.0")
    implementation(platform("org.jetbrains.kotlin:kotlin-bom"))
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    implementation("org.litote.kmongo:kmongo-coroutine:5.5.1")
    implementation("io.vertx:vertx-core:5.0.7")
    implementation("io.vertx:vertx-lang-kotlin:5.0.7")
    implementation("io.vertx:vertx-web:5.0.7")
    implementation("org.litote.kmongo:kmongo-async:5.5.1")
}

application {
    mainClass.set("MainKt")
}

tasks.withType<Jar> {
    manifest {
        attributes["Main-Class"] = "MainKt"
    }
    from(sourceSets.main.get().output)
    dependsOn(configurations.runtimeClasspath)
    from({
        configurations.runtimeClasspath.get().filter { it.name.endsWith("jar") }.map { zipTree(it) }
    })
    duplicatesStrategy = DuplicatesStrategy.EXCLUDE
}
