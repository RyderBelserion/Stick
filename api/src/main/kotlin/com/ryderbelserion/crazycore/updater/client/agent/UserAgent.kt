package com.ryderbelserion.crazycore.updater.client.agent

class UserAgent(private val project: String?, private val author: String?, private val version: String?) {

    override fun toString(): String {
        if (project == null && author == null) return "No User Agent"
        val result = StringBuilder()
        result.append(author).append("/").append(project)
        if (version != null) result.append("/").append(version)
        return result.toString().trim { it <= ' ' }
    }
}