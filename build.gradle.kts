import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import org.springframework.boot.gradle.tasks.bundling.BootJar

plugins {
    id("org.jlleitschuh.gradle.ktlint") version "11.0.0"
    id("org.springframework.boot") version "3.1.2"
    id("io.spring.dependency-management") version "1.1.0"
    id("org.jetbrains.kotlin.plugin.noarg") version "1.9.0"
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
    ktlint {
        filter {
            exclude { it.file.path.contains("$buildDir/generated/") }
        }
        disabledRules.set(setOf("import-ordering", "no-wildcard-imports", "filename"))
    }
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

        // token
        implementation("com.auth0:java-jwt:3.18.3")
        implementation("io.jsonwebtoken:jjwt:0.9.1")
        implementation("com.nimbusds:nimbus-jose-jwt:9.31")

        // security
        implementation("org.springframework.boot:spring-boot-starter-security")
        implementation("org.springframework.security:spring-security-test")
        implementation("org.springframework.security:spring-security-oauth2-client")

        implementation("com.linecorp.kotlin-jdsl:hibernate-kotlin-jdsl:2.2.1.RELEASE")
        implementation("com.linecorp.kotlin-jdsl:kotlin-jdsl-core-jakarta:2.2.1.RELEASE")

        // kotlin
        implementation("com.fasterxml.jackson.datatype:jackson-datatype-jsr310:2.13.0")
        implementation("com.fasterxml.jackson.module:jackson-module-kotlin:2.13.0")
        implementation("org.jetbrains.kotlin:kotlin-reflect")
        implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8:1.9.0")

        // jasypt
        implementation("com.github.ulisesbocchio:jasypt-spring-boot-starter:3.0.5")

        testImplementation("org.springframework.boot:spring-boot-starter-test")
        testImplementation("com.ninja-squad:springmockk:2.0.3")

        // kotest
        testImplementation("io.kotest:kotest-assertions-core-jvm:5.6.2")
        testImplementation("io.kotest:kotest-runner-junit5-jvm:5.6.2")
        testImplementation("io.mockk:mockk:1.13.5")

        // test fixture
        testImplementation("com.navercorp.fixturemonkey:fixture-monkey-starter-kotlin:0.6.3")

        // kotlin jdsl
        implementation("com.linecorp.kotlin-jdsl:spring-data-kotlin-jdsl-starter-jakarta:2.2.1.RELEASE")

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

tasks.named<BootJar>("bootJar") {
    enabled = false
}

tasks.named<Jar>("jar") {
    enabled = true
}
