package org.delcom

import io.github.cdimascio.dotenv.dotenv
import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.application.*
import io.ktor.server.netty.*
import io.ktor.server.plugins.contentnegotiation.*
import io.ktor.server.plugins.cors.routing.*
import kotlinx.serialization.json.Json
import org.delcom.module.appModule
import org.delcom.helpers.configureDatabases
import org.koin.ktor.plugin.Koin
import java.io.File

fun main(args: Array<String>) {
    loadEnvironmentVariables()

    EngineMain.main(args)
}

private fun loadEnvironmentVariables() {
    val envFilename = when {
        File(".env").exists() -> ".env"
        File(".env.example").exists() -> ".env.example"
        else -> null
    }

    if (envFilename == null) {
        return
    }

    val env = dotenv {
        directory = "."
        filename = envFilename
        ignoreIfMissing = true
        ignoreIfMalformed = true
    }

    env.entries().forEach {
        if (System.getProperty(it.key).isNullOrBlank()) {
            System.setProperty(it.key, it.value)
        }
    }
}

fun Application.module() {

    install(CORS) {
        anyHost()
    }

    install(ContentNegotiation) {
        json(
            Json {
                explicitNulls = false
                prettyPrint = true
                ignoreUnknownKeys = true
            }
        )
    }

    install(Koin) {
        modules(appModule)
    }

    configureDatabases()
    configureRouting()
}
