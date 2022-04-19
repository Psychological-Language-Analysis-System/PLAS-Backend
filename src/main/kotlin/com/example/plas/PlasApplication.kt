package com.example.plas

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration
import org.springframework.boot.runApplication

@SpringBootApplication(exclude = [SecurityAutoConfiguration::class])
class PlasApplication

fun main(args: Array<String>) {
    runApplication<PlasApplication>(*args)
}
