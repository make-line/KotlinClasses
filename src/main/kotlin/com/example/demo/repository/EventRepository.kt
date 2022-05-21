package com.example.demo.repository;

import com.example.demo.model.Event
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface EventRepository : JpaRepository<Event, Long> {
    @Query("SELECT e FROM Event e WHERE e.status='NEW'")
    fun findAllEventsNew(): Collection<Event?>?
}