package com.github.jairrab.fileutilities

import java.io.*

object FileUtilities {
    fun copyFileToDirectory(
        inputStream: InputStream,
        outputDirectory: File,
        copiedFileName: String
    ): File {
        outputDirectory.mkdirs()
        val outputFile = File(outputDirectory, copiedFileName)
        inputStream.use { it.copyTo(FileOutputStream(outputFile)) }
        return outputFile
    }

    fun copyFile(
        inputStream: InputStream,
        outputFile: File,
    ): File {
        inputStream.use { it.copyTo(FileOutputStream(outputFile)) }
        return outputFile
    }

    fun copyFile(
        fileToCopy: File,
        outputStream: OutputStream,
    ) {
        outputStream.use { it.write(fileToCopy.readBytes()) }
    }

    fun copyFile(source: String, destination: String): File {
        val inputStream = FileInputStream(File(source))
        val destinationFile = File(destination)
        return copyFile(inputStream, destinationFile)
    }

    fun createFile(fileName: String, directory: String): File {
        val file = File("$directory/$fileName")
        if (file.parentFile?.isDirectory == false) file.parentFile?.mkdirs()
        return file
    }

    fun deleteAllFiles(directory: String, list: List<String>?) {
        list?.forEach {
            val file = File("$directory/$it")
            file.delete()
        }
    }

    fun getExtension(fileName:String): String {
        return fileName.substringAfterLast('.', "")
    }

    fun deleteDirectoryFiles(directory: File) {
        directory.deleteRecursively()
    }
}