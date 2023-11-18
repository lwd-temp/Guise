pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    @Suppress("UnstableApiUsage") // TODO: Remove once KTIJ-19369 is fixed
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    @Suppress("UnstableApiUsage") // TODO: Remove once KTIJ-19369 is fixed
    repositories {
        google()
        mavenCentral()
        mavenLocal {
            include("io.github.libxposed")
        }
        maven { url = uri("https://s01.oss.sonatype.org/content/repositories/releases/") }
    }
}

rootProject.name = "Guise"
include(":app")
