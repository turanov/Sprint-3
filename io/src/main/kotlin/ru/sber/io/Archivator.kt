package ru.sber.io

import java.io.*
import java.util.zip.*

/**
 * Реализовать методы архивации и разархивации файла.
 * Для реализиации использовать ZipInputStream и ZipOutputStream.
 */
class Archivator {

    /**
     * Метод, который архивирует файл logfile.log в архив logfile.zip.
     * Архив должен располагаться в том же каталоге, что и исходной файл.
     */
    fun zipLogfile(filePath: String = "io/logfile.log", outputZip: String = "io/logfile.zip") {
        try {
            val fout = FileOutputStream(outputZip)
            val fin = FileInputStream(filePath)
            val zout = ZipOutputStream(fout)
            val buffer = ByteArray(fin.available())
            fin.use { stream ->
                stream.read(buffer)
            }
            zout.use { stream ->
                val entry = ZipEntry(filePath)
                stream.putNextEntry(entry)
                stream.write(buffer)
                stream.closeEntry()
            }
        } catch (e: Exception) {
            println(e.message)
        }
    }

    /**
     * Метод, который извлекает файл из архива.
     * Извлечь из архива logfile.zip файл и сохарнить его в том же каталоге с именем unzippedLogfile.log
     */
    fun unzipLogfile(zipFilePath: String = "io/logfile.zip", destDirectory: String = "io/unzippedLogfile.log") {
        try {
            val fin = FileInputStream(zipFilePath)
            val zin = ZipInputStream(fin)
            val fout = FileOutputStream(destDirectory)
            var buffer: ByteArray
            zin.use { stream ->
                stream.nextEntry
                buffer = stream.readBytes()
            }
            fout.use { stream ->
                stream.write(buffer)
            }
        } catch (e: Exception) {
            println(e.message)
        }
    }
}

fun main() {
    val archiver = Archivator()
    archiver.zipLogfile()
    archiver.unzipLogfile()
}