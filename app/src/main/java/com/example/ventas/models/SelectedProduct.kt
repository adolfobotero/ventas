package com.example.ventas.models

data class SelectedProduct(
    val name: String,
    val unitPrice: Double,
    val quantity: Int,
    val total: Double
)
