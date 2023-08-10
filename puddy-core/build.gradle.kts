val jar: Jar by tasks
val bootJar: org.springframework.boot.gradle.tasks.bundling.BootJar by tasks

bootJar.enabled = false
jar.enabled = true

dependencies {
    implementation("javax.xml.bind:jaxb-api:2.3.0")
    implementation("org.springframework.cloud:spring-cloud-starter-aws:2.2.6.RELEASE")
}
