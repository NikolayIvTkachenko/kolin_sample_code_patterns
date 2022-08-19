import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import org.jetbrains.kotlin.ir.backend.js.compile

kotlin {
    experimental{
        coroutines
    }
}

plugins {
    kotlin("jvm") version "1.6.21"
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))
    implementation("io.reactivex.rxjava2:rxjava:2.2.21")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    implementation("io.reactivex.rxjava2:rxkotlin:2.4.0")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.2")
    implementation("com.google.guava:guava:31.1-jre")
    //implementation("org.jetbrains.kotlinx:kotlinx.dom:0.0.10")
    //implementation(kotlin("io.reactivex.rxjava2:rxjava:2.1.14"))
    //compile "io.reactivex.rxjava2:rxjava:2.1.14"
    //implementation 'io.reactivex.rxjava2:rxjava:2.1.14'
}

tasks.test {
    useJUnitPlatform()
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}

//compile( "io.reactivex.rxjava2:rxjava:2.1.14")

