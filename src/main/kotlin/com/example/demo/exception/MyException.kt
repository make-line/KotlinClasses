package com.example.demo.exception

class MyException(message: String) : RuntimeException(message) {
    fun handleException(e: MyException) {}
}