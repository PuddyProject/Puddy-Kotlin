import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

dependencies {
    api(project(":puddy-common"))
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict")
        jvmTarget = "17"
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}
