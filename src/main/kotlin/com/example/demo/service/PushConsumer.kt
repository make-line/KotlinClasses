package com.example.demo.service

import com.example.demo.model.Event


class PushConsumer:Consumer() {
    override fun getMessage(event: Event) {
        println("Push from $event service")
        println(event.body)
    }
}