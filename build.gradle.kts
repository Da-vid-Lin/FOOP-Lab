plugins {
    java
    application
}

group = "ecs658u"
version = "2026.1"

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(17))
    }
}

application {
    mainClass.set("shapes.TangramPuzzle")
}

tasks.named<JavaExec>("run") {
    project.findProperty("mainClass")?.toString()?.let(mainClass::set)
}

dependencies {
    implementation("com.github.simonlucas:kplotlib:v1.0.6")
    implementation("com.github.mauricioaniche:ck:0.7.0")
    implementation("com.google.code.gson:gson:2.11.0")
    implementation("org.eclipse.jdt:org.eclipse.jdt.core:3.38.0")

    testImplementation(platform("org.junit:junit-bom:5.11.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

val studentTestSourceSet = sourceSets.create("studentTest") {
    java.srcDir("src/studentTest/java")
    resources.srcDir("src/studentTest/resources")
    compileClasspath += sourceSets.main.get().output
    runtimeClasspath += output + compileClasspath
}

configurations[studentTestSourceSet.implementationConfigurationName]
    .extendsFrom(configurations.testImplementation.get())
configurations[studentTestSourceSet.runtimeOnlyConfigurationName]
    .extendsFrom(configurations.testRuntimeOnly.get())

tasks.test {
    useJUnitPlatform()
    testLogging {
        events("PASSED", "FAILED", "SKIPPED")
    }
}

val studentTest by tasks.registering(Test::class) {
    group = LifecycleBasePlugin.VERIFICATION_GROUP
    description = "Run tests written by the student in src/studentTest/java."
    testClassesDirs = studentTestSourceSet.output.classesDirs
    classpath = studentTestSourceSet.runtimeClasspath
    useJUnitPlatform()
    shouldRunAfter(tasks.test)
    testLogging {
        events("PASSED", "FAILED", "SKIPPED")
    }
}

tasks.check {
    dependsOn(studentTest)
}

tasks.register<JavaExec>("ckMetrics") {
    group = "course metrics"
    description = "Run CK metrics independently of the normal build."
    dependsOn(tasks.classes)
    classpath = sourceSets.main.get().runtimeClasspath
    mainClass.set("metrics.CKMetricsRunnerWithMethods")
}

tasks.register<JavaExec>("jdtComplexity") {
    group = "course metrics"
    description = "Run the JDT cyclomatic-complexity example independently of check."
    dependsOn(tasks.classes)
    classpath = sourceSets.main.get().runtimeClasspath
    mainClass.set("metrics.CyclomaticComplexityCalculator")
}
