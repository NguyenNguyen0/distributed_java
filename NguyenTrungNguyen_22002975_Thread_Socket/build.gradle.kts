plugins {
    id("java")
}

group = "java.course"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")

    // Data faker
    implementation("net.datafaker:datafaker:2.4.2")

    // Parson
    implementation("org.eclipse.parsson:parsson:1.1.7")

    // Yasson
    testImplementation("org.eclipse:yasson:3.0.4")

    // JSON API
    implementation("jakarta.json:jakarta.json-api:2.1.3")
    implementation("jakarta.json.bind:jakarta.json.bind-api:3.0.1")
    implementation("org.eclipse.parsson:parsson:1.1.7")

    // Lombok
    compileOnly("org.projectlombok:lombok:1.18.36")
    annotationProcessor("org.projectlombok:lombok:1.18.36")
}

tasks.test {
    useJUnitPlatform()
}