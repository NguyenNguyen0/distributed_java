plugins {
    id("java")
}

group = "vn.edu.iuh.fit"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.9.1"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    // https://mvnrepository.com/artifact/org.projectlombok/lombok
    compileOnly("org.projectlombok:lombok:1.18.36")
    annotationProcessor("org.projectlombok:lombok:1.18.36")
    // https://mvnrepository.com/artifact/jakarta.json/jakarta.json-api
    implementation("jakarta.json:jakarta.json-api:2.1.3")
// https://mvnrepository.com/artifact/jakarta.json.bind/jakarta.json.bind-api
    implementation("jakarta.json.bind:jakarta.json.bind-api:3.0.1")
// https://mvnrepository.com/artifact/org.eclipse.parsson/parsson
    implementation("org.eclipse.parsson:parsson:1.1.7")

}

tasks.test {
    useJUnitPlatform()
}