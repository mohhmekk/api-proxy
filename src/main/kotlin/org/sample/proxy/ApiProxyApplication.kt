package org.sample.proxy

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication(scanBasePackageClasses=[ CacheConfig::class])
class ApiProxyApplication

fun main(args: Array<String>) {
    runApplication<ApiProxyApplication>(*args)
}