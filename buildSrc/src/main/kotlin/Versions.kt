
import org.gradle.plugin.use.PluginDependenciesSpec
import org.gradle.plugin.use.PluginDependencySpec

/**
 * Generated by https://github.com/jmfayard/buildSrcVersions
 *
 * Find which updates are available by running
 *     `$ ./gradlew buildSrcVersions`
 * This will only update the comments.
 *
 * YOU are responsible for updating manually the dependency version.
 */
object Versions {
    const val org_jetbrains_kotlinx_kotlinx_coroutines: String = "1.3.3"

    const val com_squareup_retrofit2: String = "2.7.0"

    const val androidx_databinding: String = "3.5.3"

    const val org_jetbrains_kotlin: String = "1.3.61"

    const val androidx_navigation: String = "2.1.0"

    const val androidx_room: String = "2.2.2"

    const val com_android_tools_build_gradle: String = "3.5.3"

    const val androidx_test_ext_junit: String = "1.1.1"

    const val junit_junit: String = "4.12"

    const val de_fayard_buildsrcversions_gradle_plugin: String = "0.7.0"

    const val retrofit2_kotlin_coroutines_adapter: String = "0.9.2"

    const val koin_androidx_viewmodel: String = "2.0.1"

    const val lifecycle_extensions: String = "2.1.0"

    const val logging_interceptor: String = "4.2.2"

    const val paging_runtime_ktx: String = "2.1.0"

    const val constraintlayout: String = "1.1.3"

    const val work_runtime_ktx: String = "2.2.0"

    const val espresso_core: String = "3.2.0"

    const val activity_ktx: String = "1.0.0"

    const val fragment_ktx: String = "1.1.0"

    const val moshi_kotlin: String = "1.9.2"

    const val recyclerview: String = "1.1.0"

    const val lint_gradle: String = "26.5.3"

    const val appcompat: String = "1.1.0"

    const val liveevent: String = "1.2.0"

    const val core_ktx: String = "1.1.0"

    const val material: String = "1.2.0-alpha02"

    const val shimmer: String = "0.5.0"

    const val aapt2: String = "3.5.3-5435860"

    const val glide: String = "4.10.0"

    /**
     * Current version: "6.0.1"
     * See issue 19: How to update Gradle itself?
     * https://github.com/jmfayard/buildSrcVersions/issues/19
     */
    const val gradleLatestVersion: String = "6.0.1"
}

/**
 * See issue #47: how to update buildSrcVersions itself
 * https://github.com/jmfayard/buildSrcVersions/issues/47
 */
val PluginDependenciesSpec.buildSrcVersions: PluginDependencySpec
    inline get() =
            id("de.fayard.buildSrcVersions").version(Versions.de_fayard_buildsrcversions_gradle_plugin)
