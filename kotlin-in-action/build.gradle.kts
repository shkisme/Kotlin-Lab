plugins {
    kotlin("jvm") version "1.9.0"
}

repositories {
    mavenCentral()
    maven { setUrl("https://jitpack.io") }
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(17))
    }
}

tasks {
    test {
        useJUnitPlatform()
    }
}
