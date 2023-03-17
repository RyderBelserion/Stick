package com.ryderbelserion.crazycore

import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import com.google.gson.Gson
import com.google.gson.JsonObject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlinx.serialization.json.Json
import java.io.InputStreamReader

suspend fun main(args: Array<String>) {

    val browser = Browser("crazycrates")

    browser.add()

    println(browser.get()?.id)

}

private class Browser(private val projectName: String) {

    private val base = "https://api.modrinth.com/v2"

    private val map = hashMapOf<String, Resource>()

    private val client = HttpClient(CIO) {
        install(ContentNegotiation) {
            json(Json {
                prettyPrint = true
                isLenient = true
            })
        }
    }

    private suspend fun project(): JsonObject? {
        var jsonObject: JsonObject? = null

        runBlocking(Dispatchers.IO) {
            val response = client.get("$base/project/$projectName") {
                method = HttpMethod.Get
            }

            if (response.status.value !in 200..299) {
                println("Cannot get a response likely because you used an incorrect project name!")
                return@runBlocking
            }

            val reader = InputStreamReader(response.readBytes().inputStream())

            jsonObject = Gson().fromJson(reader, JsonObject::class.java)
        }

        return jsonObject
    }

    private suspend fun id(): String {
        return project()?.get("id").toString()
    }

    suspend fun add() {
        map.putIfAbsent(id(), Resource(id()))
    }

    suspend fun remove() {
        map.remove(id())
    }

    suspend fun get(): Resource? {
        return map[id()]
    }
}

private data class Resource(
    val id: String
)