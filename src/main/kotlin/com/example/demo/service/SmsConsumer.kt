package com.example.demo.service

import com.example.demo.model.Event


class SmsConsumer: Consumer() {
    override fun getMessage(event: Event) {
        println("Sms from $event service")
        println(event.body)
    }
}