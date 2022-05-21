package com.example.demo

import com.example.demo.enums.Status
import com.example.demo.enums.Type
import com.example.demo.model.Event
import com.example.demo.repository.EventRepository
import com.example.demo.service.ScheduledService
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import java.lang.Thread.sleep

@SpringBootTest
class DemoApplicationTests {

    @Autowired
    private lateinit var scheduledService: ScheduledService

    @Autowired
    private lateinit var eventRepository: EventRepository


    @BeforeEach
    fun setUp() {
        val event = Event()
        event.id = 1
        event.type = Type.SMS
        event.body = "Hello SMS"
        event.status = Status.NEW
        eventRepository.save(event)
    }


    @Test
    fun updateEventStatus() {
        scheduledService.send()
        sleep(100)
        val result = eventRepository.getById(1)
        Assertions.assertAll(
            { assertEquals("Hello SMS", result.body) },
            { assertEquals(Type.SMS, result.type) },
            { assertEquals(Status.DONE, result.status) },
        )
    }
}
