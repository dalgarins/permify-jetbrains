package com.mallowigi.permify.highlighter

import com.intellij.ide.plugins.PluginManagerCore
import com.intellij.openapi.extensions.PluginId
import com.intellij.util.containers.Interner
import com.mallowigi.permify.PermifyFileType
import org.jetbrains.plugins.textmate.bundles.readTextMateBundle
import org.jetbrains.plugins.textmate.language.TextMateLanguageDescriptor
import org.jetbrains.plugins.textmate.language.syntax.TextMateSyntaxTable
import java.io.File
import java.io.IOException
import java.io.UncheckedIOException
import java.nio.file.Path
import java.util.zip.ZipInputStream

private fun getBundlePath(): Path {
  try {
    val plugin = PluginManagerCore.getPlugin(PluginId.getId("com.mallowigi.permify"))
    val version = plugin?.version ?: "latest"
    val bundleDirectory = File("${plugin?.pluginPath}/bundles/$version")
    if (!bundleDirectory.exists()) {
      deleteFile(bundleDirectory.getParentFile())
      bundleDirectory.mkdirs()
      val resource = PermifyFileType::class.java.classLoader.getResourceAsStream("bundles/bundle.zip")

      extract(ZipInputStream(resource!!), bundleDirectory)
    }
    return Path.of("${bundleDirectory.path}/permify")
  } catch (ex: IOException) {
    throw UncheckedIOException(ex)
  }
}

private fun extract(zip: ZipInputStream, target: File) {
  try {
    while (true) {
      val entry = zip.nextEntry ?: break
      val file = File(target, entry.name)

      if (!file.toPath().normalize().startsWith(target.toPath())) {
        throw IOException("Bad zip entry: ${file.absolutePath}")
      }

      if (entry.isDirectory) {
        file.mkdirs()
        continue
      }

      val buffer = ByteArray(4096)
      val parent = file.parentFile
      parent.mkdirs()

      val output = file.outputStream()
      var len: Int
      while (zip.read(buffer).also { len = it } > 0) {
        output.write(buffer, 0, len)
      }
      output.close()
    }
  } finally {
    zip.close()
  }
}

private fun deleteFile(file: File) {
  if (file.isDirectory) {
    file.listFiles()?.forEach { deleteFile(it) }
  }
  file.delete()
}

fun getTextMateLanguageDescriptor(): TextMateLanguageDescriptor {
  try {
    val bundle = readTextMateBundle(getBundlePath())
    val syntax = TextMateSyntaxTable()
    val interner = Interner.createWeakInterner<CharSequence>()
    val grammars = bundle.readGrammars()
    for (grammar in grammars) {
      syntax.loadSyntax(grammar.plist.value, interner)
    }
    return TextMateLanguageDescriptor("source.perm", syntax.getSyntax("source.perm"))
  } catch (e: IOException) {
    throw RuntimeException(e)
  }
}
