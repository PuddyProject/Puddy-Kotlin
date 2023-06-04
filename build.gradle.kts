import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("org.springframework.boot") version "3.1.0"
    id("io.spring.dependency-management") version "1.1.0"
    id("org.asciidoctor.jvm.convert") version "3.3.2"
    id ("org.jetbrains.kotlin.plugin.allopen") version "1.8.21"

    kotlin("jvm") version "1.8.21"
    kotlin("plugin.spring") version "1.8.21"
    kotlin("plugin.jpa") version "1.8.21"

    kotlin("kapt") version "1.8.21"
}

group = "world"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_17

repositories {
    mavenCentral()
}

extra["snippetsDir"] = file("build/generated-snippets")

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.boot:spring-boot-starter-data-redis")
    implementation("org.springframework.boot:spring-boot-starter-oauth2-client")
    implementation("org.springframework.boot:spring-boot-starter-security")
    implementation("org.springframework.boot:spring-boot-starter-validation")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    runtimeOnly("com.mysql:mysql-connector-j")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("org.springframework.security:spring-security-test")


    testImplementation("io.mockk:mockk:1.13.5")


    //kotest
    testImplementation("io.kotest:kotest-runner-junit5-jvm:4.6.0")
    testImplementation("io.kotest:kotest-assertions-core:4.6.0")
    testImplementation("io.mockk:mockk:1.11.0")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("io.projectreactor:reactor-test")

    //jasypt
    implementation("com.github.ulisesbocchio:jasypt-spring-boot-starter:3.0.5")


    //jwt token
    implementation ("com.auth0:java-jwt:3.18.3")
    implementation ("io.jsonwebtoken:jjwt:0.9.1")
    implementation ("com.nimbusds:nimbus-jose-jwt:9.31")


    //S3
    implementation("io.awspring.cloud:spring-cloud-starter-aws:2.4.4")
    implementation("javax.xml.bind:jaxb-api:2.3.1")
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict")
        jvmTarget = "17"
    }
}

tasks.withType<Jar> {
    enabled = false
}

tasks.withType<Test> {
    useJUnitPlatform()
}

