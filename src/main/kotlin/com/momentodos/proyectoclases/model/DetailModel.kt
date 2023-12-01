package com.momentodos.proyectoclases.model

import jakarta.persistence.*

@Entity
@Table(name = "Detail")
class DetailModel {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(updatable = false)
    var id  : Long? = null
    var price: String? = null

    @ManyToOne
    @JoinColumn(name = "invoice_id", nullable = false)
    var invoice: InvoiceModel?=null
    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    var product: ProductModel?=null
}