import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import java.io.ByteArrayOutputStream
import java.nio.charset.StandardCharsets

buildscript {
    repositories {
        gradlePluginPortal()
        jcenter()
    }
    dependencies {
        classpath("org.mariadb.jdbc:mariadb-java-client:2.3.0")
        classpath("org.springframework.boot:spring-boot-gradle-plugin:2.2.6.RELEASE")
        //classpath("gradle.plugin.com.github.spotbugs:spotbugs-gradle-plugin:1.7.1")
    }
}

plugins {
    id("java")
    kotlin("jvm").version( "1.3.72")
    kotlin("plugin.spring").version("1.3.72")
    id("org.springframework.boot").version("2.2.6.RELEASE")
    id("io.spring.dependency-management").version("1.0.9.RELEASE")
    id("org.flywaydb.flyway").version("5.2.4")
    //id "com.gorylenko.gradle-git-properties" version "1.4.17"
    id("checkstyle")
    id("pmd")
    //id 'findbugs'
    id("idea")
    id("eclipse")
    //id "com.github.spotbugs" version "1.7.1"
    // id("io.freefair.git-version") version "5.0.0"
}

repositories {
    jcenter()
}

apply {
    from("flyway.gradle")
}
apply {
    from("codestyle.gradle")
}

group = "de.betting-game"
version = "0.2.0"
description = "Betting game"

dependencies {
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8:1.3.72")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-devtools")
    implementation("org.springframework.boot:spring-boot-starter-thymeleaf")
    implementation("org.springframework.boot:spring-boot-starter-security")
    implementation("org.springframework.boot:spring-boot-starter-actuator")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.thymeleaf.extras:thymeleaf-extras-springsecurity5:3.0.4.RELEASE")

    implementation("org.flywaydb:flyway-core:5.2.4")
    implementation("org.mariadb.jdbc:mariadb-java-client:2.3.0")
    implementation("org.hibernate:hibernate-java8")
    implementation("javax.xml.bind:jaxb-api")
    implementation("org.apache.commons:commons-lang3:3.9")

    runtimeOnly("org.springframework.boot:spring-boot-properties-migrator")

    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation(kotlin("test"))
    testImplementation(kotlin("test-junit"))
}

tasks.named<KotlinCompile>("compileKotlin") {
    kotlinOptions.jvmTarget = "11"
    kotlinOptions.freeCompilerArgs = listOf("-Xjvm-default=compatibility")
}

var gitversion: Gitversion = Gitversion()

var git = "test"
val process = ProcessBuilder().command("git", "describe").start()
process.outputStream.bufferedWriter(StandardCharsets.UTF_8).write(git)

exec {
    commandLine("git", "describe")
    standardOutput = ByteArrayOutputStream()
    gitversion.text = standardOutput.toString()
    logger.quiet("Test {}", gitversion)
}

logger.quiet("Current Git version: {}", git)

task<JavaCompile>("JavaCompile") {
    sourceCompatibility = JavaVersion.VERSION_11.name
    targetCompatibility = JavaVersion.VERSION_11.name
}

val processResources: ProcessResources by tasks
processResources.filesMatching("**/Version_*.properties") {
        expand(mutableMapOf("gitversion" to gitversion))
    }

tasks.named<Wrapper>("wrapper") {
    gradleVersion = "6.3"
}

class Gitversion {
    lateinit var text: String
    override fun toString(): String {
        return text
    }
}