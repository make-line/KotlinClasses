package com.example.demo.client

import com.example.demo.model.School
import com.example.demo.model.SchoolDto
import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.reactive.function.client.awaitBody
import reactor.core.publisher.Mono

@Component
class SchoolClient(private var client: WebClient){
    suspend fun schoolTransform(schoolDto: SchoolDto): School {
        return client.post()
            .uri("/schools")
            .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
            .body(Mono.just(schoolDto), SchoolDto::class.java)
            .retrieve()
            .awaitBody()
    }
}