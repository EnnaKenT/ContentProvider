const val kotlinVersion = "1.5.0"
const val roomVersion = "2.3.0"

object AndroidSdk {
    const val min = 24
    const val compile = 29
    const val target = compile
    const val androidJUnitRunner = "androidx.test.runner.AndroidJUnitRunner"
}

object ContentProvider {
    const val name = "PROVIDER_AUTHORITY"
    const val value = "com.example.contentprovider.provider"
}

object FileTree {
    val fileTree = mapOf("dir" to "libs", "include" to listOf("*.jar"))
}