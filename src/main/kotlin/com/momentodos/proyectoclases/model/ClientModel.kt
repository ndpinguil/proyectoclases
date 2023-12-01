package com.momentodos.proyectoclases.model

import jakarta.persistence.*

@Entity
@Table(name = "Client")
class ClientModel {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(updatable = false)
    var id  : Long? = null
    var nui: String? = null
    var full_name: String? = null
    var address: String? = null
}