package com.momentodos.proyectoclases.model

import jakarta.persistence.*

@Entity
@Table(name = "Invoice")
class InvoiceModel {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(updatable = false)
    var id  : Long? = null
    var code: String? = null
    var create_at: String? = null
    var total: String? = null
    @ManyToOne
    @JoinColumn(name = "client_id", nullable = false)
    var cliente: ClientModel?=null

}