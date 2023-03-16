package com.ryderbelserion.crazycore

import com.ryderbelserion.crazycore.updater.Browser

suspend fun main(args: Array<String>) {

    val browser = Browser("crazycrates")

    browser.add()

    println(browser.get()?.id)

}