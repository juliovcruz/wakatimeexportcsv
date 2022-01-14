package com.juliocruz.wakaexportcsv.entity

import java.io.BufferedWriter
import java.io.FileWriter
import org.apache.commons.csv.CSVFormat
import org.apache.commons.csv.CSVPrinter

data class FileExport(
    val fileWriter: FileWriter,
    val writer: BufferedWriter,
    val csvPrinter: CSVPrinter,
    val category: Category
) {
    companion object {
        fun create(name: Category): FileExport {
            val fileWriter = FileWriter("$name.csv")
            val writer = BufferedWriter(fileWriter)
            val csvPrinter = CSVPrinter(writer, CSVFormat.DEFAULT
                .withHeader("Date", name.name, "Minutes", "Text Time"))

            return FileExport(
                category = name,
                fileWriter = fileWriter,
                writer = writer,
                csvPrinter = csvPrinter
            )
        }
    }
}

enum class Category {
    LANGUAGES,
    EDITORS,
    MACHINES
}