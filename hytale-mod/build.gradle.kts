plugins {
    id("java")
}

group = "com.ageofempires"
version = "2.0.0-HYTALE"
java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(25))
    }
}
repositories {
    mavenCentral()
}

dependencies {
    // Hytale Server API - Add Manually in lib/
    compileOnly(files("lib/HytaleServer.jar"))
}

tasks.jar {
    archiveBaseName.set("AgeOfEmpires")
}

// Task to run the Hytale server (like Minecraft Paper runServer)
tasks.register<JavaExec>("runServer") {
    group = "hytale"
    description = "Run the Hytale server with the plugin"

    // Use the Java toolchain
    javaLauncher.set(javaToolchains.launcherFor {
        languageVersion.set(JavaLanguageVersion.of(25))
    })

    // Main class for Hytale Server
    mainClass.set("com.hypixel.hytale.server.HytaleServer")

    // Classpath: HytaleServer.jar
    classpath = files("lib/HytaleServer.jar")

    // Working directory
    workingDir = file("run")

    // JVM arguments
    jvmArgs = listOf(
        "-Xms4G",
        "-Xmx4G"
    )

    // Program arguments
    args = listOf(
        "--assets", "../HytaleAssets"
    )

    // Create run directory if it doesn't exist
    doFirst {
        val runDir = file("run")
        if (!runDir.exists()) {
            runDir.mkdirs()
        }

        val modsDir = file("run/mods")
        if (!modsDir.exists()) {
            modsDir.mkdirs()
        }

        // Copy the plugin jar to the mods folder
        val pluginJar = tasks.jar.get().archiveFile.get().asFile
        if (pluginJar.exists()) {
            pluginJar.copyTo(file("run/mods/${pluginJar.name}"), overwrite = true)
        }
    }

    // Depend on jar task to build plugin first
    dependsOn(tasks.jar)
}