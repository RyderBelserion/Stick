package com.ryderbelserion.crazycore.updater.client

import com.ryderbelserion.crazycore.updater.client.agent.UserAgent

abstract class HttpClient {
    private val url = "https://api.modrinth.com/v2"
    private val userAgent: UserAgent? = null


}