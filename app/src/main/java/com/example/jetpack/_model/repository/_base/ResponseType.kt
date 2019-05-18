package com.example.jetpack._model.repository._base

enum class ResponseType(private val code: Int, private val value: String) {

    OK(200, "OK"),
    BAD(400, "BAD")
}
