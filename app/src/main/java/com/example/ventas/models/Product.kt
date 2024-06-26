package com.example.ventas.models

data class Product(
    val idProduct: Int,
    val nameProduct: String,
    val priceProduct: Double
){
    override fun toString(): String {
        return nameProduct
    }
}
