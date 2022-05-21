package com.example.demo.service

import com.example.demo.model.Event
import com.example.demo.repository.EventRepository


open class Consumer {
    open fun getMessage(event: Event){
        println("Message from $event service")
    }


}