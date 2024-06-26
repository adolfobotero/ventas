package com.example.ventas.models

import com.example.ventas.models.database.entities.CustomerEntity

class Customers {
    var customers = ArrayList<CustomerEntity>()

    constructor(customers: ArrayList<CustomerEntity>){
        this.customers = customers
    }
}