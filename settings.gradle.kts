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
    }
}

rootProject.name = "Guise"
include(":app")
