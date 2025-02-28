plugins {
    id("java")
}

group = "java.course"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.projectlombok:lombok:1.18.36")
    annotationProcessor("org.projectlombok:lombok:1.18.36")

    implementation ("jakarta.json:jakarta.json-api:2.1.3")
    implementation ("jakarta.json.bind:jakarta.json.bind-api:3.0.1")
    implementation ("org.eclipse.parsson:parsson:1.1.7")

    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
}

tasks.test {
    useJUnitPlatform()
}