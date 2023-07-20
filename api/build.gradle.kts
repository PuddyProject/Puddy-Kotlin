val jar: Jar by tasks
val bootJar: org.springframework.boot.gradle.tasks.bundling.BootJar by tasks

bootJar.enabled = true
jar.enabled = false

dependencies {
    api(project(":question"))
    api(project(":user"))
    // security
    implementation("org.springframework.boot:spring-boot-starter-security")
    implementation("org.springframework.security:spring-security-test")
    implementation("org.springframework.security:spring-security-oauth2-client")
}
