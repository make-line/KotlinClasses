package com.example.demo.model

import com.example.demo.enums.Status
import com.example.demo.enums.Type
import java.io.Serializable
import javax.persistence.*

@Entity
@Table(name = "events")
class Event : Serializable {
    @Id
    @Column(name = "id", nullable = false)
    var id: Long? = null

    @Enumerated(EnumType.STRING)
    @Column(name = "type")
    var type: Type? = null

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    var status: Status? = null

    @Column(name = "body")
    var body: String? = null
}
