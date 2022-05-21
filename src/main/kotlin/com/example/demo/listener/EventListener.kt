package com.example.demo.listener


import com.example.demo.enums.Status
import com.example.demo.enums.Type
import com.example.demo.model.Event
import com.example.demo.repository.EventRepository
import com.example.demo.service.Consumer
import com.example.demo.service.EmailConsumer
import com.example.demo.service.PushConsumer
import com.example.demo.service.SmsConsumer
import org.springframework.jms.annotation.JmsListener
import org.springframework.stereotype.Service

@Service
class EventListener(var eventRepository: EventRepository) {

    @JmsListener(destination = "events")
    fun consume(event: Event) {
        if (event.status == Status.IN_PROCESS) {
            var consumer = Consumer()
            try {
                when (event.type) {
                    Type.EMAIL -> {
                        consumer = EmailConsumer()
                    }
                    Type.SMS -> {
                        consumer = SmsConsumer()
                    }
                    Type.PUSH -> {
                        consumer = PushConsumer()
                    }
                }
                consumer.getMessage(event)
                event.status = Status.DONE
                eventRepository.save(event)
            } catch (e: Exception) {
                event.status = Status.ERROR
                eventRepository.save(event)
                throw e
            }
        }
    }
}