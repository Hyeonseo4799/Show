pluginManagement {
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}
rootProject.name = "Show"
include(":app")
include(":core:network")
include(":core:data")
include(":core:model")
include(":core:common")
include(":feature:detail")
include(":feature:search")
include(":feature:result")
include(":core:ui")
