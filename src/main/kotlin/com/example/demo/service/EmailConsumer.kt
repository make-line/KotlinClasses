package com.example.demo.service

import com.example.demo.model.Event

class EmailConsumer: Consumer() {
    override fun getMessage(event: Event) {
        println("Email from $event service")
        println(event.body)
    }
}