package com.juliocruz.wakaexportcsv

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class WakaApplication

fun main(args: Array<String>) {
	runApplication<WakaApplication>(*args)
}
