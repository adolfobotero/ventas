package com.example.ventas.models

data class Customer(
    val nameCustomer: String,
    val ccCustomer: String
){
    override fun toString(): String {
        return nameCustomer
    }
}
