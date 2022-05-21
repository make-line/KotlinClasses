package com.example.demo.service

import com.example.demo.enums.Status
import com.example.demo.repository.EventRepository
import org.springframework.jms.core.JmsTemplate
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Service

@Service
class ScheduledService(var eventRepository: EventRepository, val jmsTemplate: JmsTemplate) {
    @Scheduled(cron = "*/10 * * * * *")
    fun send() {
        eventRepository.findAllEventsNew()?.forEach {
            try {
                if (it != null) {
                    it.status = Status.IN_PROCESS
                    eventRepository.save(it)
                }
                if (it != null) {
                    jmsTemplate.send("events") { session ->
                        session.createObjectMessage(it)
                    }
                }
            } catch (e: Exception) {
                if (it != null) {
                    it.status = Status.ERROR
                    eventRepository.save(it)
                }
                throw e
            }
        }
    }
}