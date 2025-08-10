dependencies {
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.data:spring-data-commons")
    implementation("org.springframework:spring-tx")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit5")
    testImplementation("org.testcontainers:junit-jupiter")
    testImplementation("io.mockk:mockk:1.13.5")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
    testFixturesImplementation("com.appmattus.fixture:fixture:1.2.0")
}
