package ru.sber.nio

import java.nio.file.Files
import java.nio.file.Paths
import kotlin.io.path.exists
import kotlin.io.path.isDirectory
import kotlin.io.path.writeText

/**
 * Реализовать простой аналог утилиты grep с использованием калссов из пакета java.nio.
 */
class Grep {
    /**
     * Метод должен выполнить поиск подстроки subString во всех файлах каталога logs.
     * Каталог logs размещен в данном проекте (io/logs) и внутри содержит другие каталоги.
     * Результатом работы метода должен быть файл в каталоге io(на том же уровне, что и каталог logs), с названием result.txt.
     * Формат содержимого файла result.txt следующий:
     * имя файла, в котором найдена подстрока : номер строки в файле : содержимое найденной строки
     * Результирующий файл должен содержать данные о найденной подстроке во всех файлах.
     * Пример для подстроки "22/Jan/2001:14:27:46":
     * 22-01-2001-1.log : 3 : 192.168.1.1 - - [22/Jan/2001:14:27:46 +0000] "POST /files HTTP/1.1" 200 - "-"
     */
    fun find(subString: String) {
        try {
            val path = Paths.get("io/logs")
            val pathToResult = Paths.get("io/result.txt")
            val pathToWrite = if (!pathToResult.exists()) Files.createFile(pathToResult) else pathToResult
            var foundLines = ""
            Files.walk(path).filter { !it.isDirectory() }.forEach {
                val fileContent = Files.lines(Paths.get(it.toString()))
                var numberLine = 1
                for (line in fileContent) {
                    if (line.contains(subString))
                        foundLines += "${it.fileName} : ${numberLine++} : $line\n"
                }
            }
            pathToWrite.writeText(foundLines)
        } catch (e: Exception) {
            println(e.message)
        }
    }
}

fun main() {
    val grep = Grep()
    grep.find("22/Jan/2001:14:27:46")
}