import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

val jar: Jar by tasks
val bootJar: org.springframework.boot.gradle.tasks.bundling.BootJar by tasks

bootJar.enabled = false
jar.enabled = true

plugins {
    id("org.jlleitschuh.gradle.ktlint") version "10.3.0"

    id("org.springframework.boot") version "3.1.1"
    id("io.spring.dependency-management") version "1.1.0"
    kotlin("jvm") version "1.9.0"
    kotlin("plugin.spring") version "1.9.0"
    kotlin("plugin.jpa") version "1.9.0"
    kotlin("plugin.allopen") version "1.9.0"
    kotlin("kapt") version "1.9.0"
}

group = "world.puddy"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_17

repositories {
    mavenCentral()
}

allprojects {
    apply(plugin = "org.jlleitschuh.gradle.ktlint")
    apply(plugin = "kotlin-allopen")
    apply(plugin = "kotlin-jpa")
    apply(plugin = "org.jetbrains.kotlin.plugin.noarg")
}

subprojects {
    apply(plugin = "kotlin")
    apply(plugin = "org.jetbrains.kotlin.plugin.spring")
    apply(plugin = "org.springframework.boot")
    apply(plugin = "io.spring.dependency-management")

    java.sourceCompatibility = JavaVersion.VERSION_17
    val implementation by configurations

    dependencies {

        // web
        implementation("org.springframework.boot:spring-boot-starter-web")
        implementation("org.springframework.boot:spring-boot-starter")
        implementation("org.springframework.boot:spring-boot-starter-validation")
        // jpa
        implementation("org.springframework.boot:spring-boot-starter-data-jpa")

        // security
        implementation("org.springframework.boot:spring-boot-starter-security")
        implementation("org.springframework.security:spring-security-test")
        implementation("org.springframework.security:spring-security-oauth2-client")

        implementation("com.linecorp.kotlin-jdsl:hibernate-kotlin-jdsl:2.2.1.RELEASE")
        implementation("com.linecorp.kotlin-jdsl:kotlin-jdsl-core-jakarta:2.2.1.RELEASE")

        // kotlin
        implementation("com.fasterxml.jackson.datatype:jackson-datatype-jsr310:2.13.0")
        implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
        implementation("org.jetbrains.kotlin:kotlin-reflect")
        implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8:1.9.0")
        implementation("com.fasterxml.jackson.datatype:jackson-datatype-jsr310:2.12.1")

        // jasypt
        implementation("com.github.ulisesbocchio:jasypt-spring-boot-starter:3.0.5")
        testImplementation("org.springframework.boot:spring-boot-starter-test")
        testImplementation("com.ninja-squad:springmockk:2.0.3")

        // token
        implementation("com.auth0:java-jwt:3.18.3")
        implementation("io.jsonwebtoken:jjwt:0.9.1")
        implementation("com.nimbusds:nimbus-jose-jwt:9.31")

        // kotest
        testImplementation("io.kotest:kotest-assertions-core-jvm:5.6.2")
        testImplementation("io.kotest:kotest-runner-junit5-jvm:5.6.2")
        testImplementation("io.mockk:mockk:1.13.5")

        // redis
        implementation("org.springframework.boot:spring-boot-starter-data-redis")

        // Disk I/O
        runtimeOnly("com.h2database:h2")
        runtimeOnly("com.mysql:mysql-connector-j")
    }

    tasks.withType<KotlinCompile> {
        kotlinOptions.jvmTarget = "17"
    }
    repositories {
        mavenCentral()
        maven { url = uri("https://repo.spring.io/milestone") }
        maven { url = uri("https://repo.spring.io/snapshot") }
    }

    dependencies {
        implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
        implementation("org.jetbrains.kotlin:kotlin-reflect")
        implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
        testImplementation("org.springframework.boot:spring-boot-starter-test")
    }

    tasks.withType<KotlinCompile> {
        kotlinOptions {
            freeCompilerArgs = listOf("-Xjsr305=strict")
            jvmTarget = "17"
        }
    }

    tasks.withType<Test> {
        useJUnitPlatform()
        testLogging {
            showStackTraces = true
            exceptionFormat = org.gradle.api.tasks.testing.logging.TestExceptionFormat.FULL
        }
    }
}
