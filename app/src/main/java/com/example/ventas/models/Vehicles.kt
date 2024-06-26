package com.example.ventas.models

import com.example.ventas.models.database.entities.VehicleEntity

class Vehicles {
    var vehicles = ArrayList<VehicleEntity>()

    constructor(vehicles: ArrayList<VehicleEntity>){
        this.vehicles = vehicles
    }

    data class VehicleWithProduct(
        val idVehiculo: Int,
        val idProducto: Int,
        val tipoVehiculo: String,
        val modeloVehiculo: Int,
        val colorVehiculo: String,
        val fotoVehiculo: String,
        val nombreProducto: String,
        val valorProducto: Double
    )
}