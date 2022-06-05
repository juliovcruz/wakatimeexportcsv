package com.juliocruz.wakaexportcsv.endpoint

import com.juliocruz.wakaexportcsv.entity.Category
import com.juliocruz.wakaexportcsv.entity.FileExport
import com.juliocruz.wakaexportcsv.request.Request
import com.juliocruz.wakaexportcsv.request.Struct
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import java.math.BigDecimal

@RestController
class Endpoint {

    @PostMapping(path = ["/"])
    fun post(@RequestBody request: Request){

        val fileExports = listOf(
            FileExport.create(name = Category.LANGUAGES),
            FileExport.create(name = Category.EDITORS),
            FileExport.create(name = Category.MACHINES)
        )

        request.days.forEach {
            val date = it.date.split("-").let { "${it[1]}/${it[2]}/${it[0]}" }

            csvExport(structs = it.editors, date = date, exceptNames = listOf("Zsh"), fileExport = fileExports.first { it.category == Category.EDITORS })
            csvExport(structs = it.machines, date = date, fileExport = fileExports.first { it.category == Category.MACHINES })
            csvExport(structs = it.languages, date = date, exceptNames = listOf("sh"), fileExport = fileExports.first { it.category == Category.LANGUAGES })
        }

        fileExports.forEach {
            it.csvPrinter.flush()
            it.csvPrinter.close()
        }
    }

    fun csvExport(
        structs: List<Struct>,
        exceptNames: List<String> = emptyList(),
        date: String,
        fileExport: FileExport
    ) {
        structs.forEach {
            try {
                val minutes = (it.hours * 60) + it.minutes
                val hours = minutes.toDouble() / 60

                if (minutes == 0 || exceptNames.equals(it.name)) return@forEach

                val data = listOf(
                    date,
                    it.name,
                    hours,
                    it.text
                )

                fileExport.csvPrinter.printRecord(data);
            } catch (exception: Throwable) {
                println(exception)
            }
        }
    }
}