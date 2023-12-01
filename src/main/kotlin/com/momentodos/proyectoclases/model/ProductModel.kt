package com.momentodos.proyectoclases.model

import jakarta.persistence.*

@Entity
@Table(name = "Product")
class ProductModel {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(updatable = false)
    var id  : Long? = null
    var description: String? = null
    var brand: String? = null
    var price: String? = null
    var stock: String? = null

}