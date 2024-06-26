package com.example.ventas.models.database.dao

import androidx.room.*
import com.example.ventas.models.Vehicles
import com.example.ventas.models.database.entities.VehicleEntity

@Dao
interface VehicleDao {
    @Insert
    fun insertVehicle(vehicle: VehicleEntity)

    @Delete
    fun deleteVehicle(vehicle: VehicleEntity)

    @Update
    fun updateVehicle(vehicle: VehicleEntity)

    @Query(
        """
        SELECT v.idVehiculo, v.idProducto, v.tipoVehiculo, v.modeloVehiculo, v.colorVehiculo, v.fotoVehiculo, p.nombreProducto, p.valorProducto
        FROM tbVehiculos v
        INNER JOIN tbProductos p ON v.idProducto = p.idProducto
    """
    )
    fun getAllVehiclesWithProduct(): List<Vehicles.VehicleWithProduct>

    @Query("SELECT * FROM tbVehiculos")
    fun getAllVehicles(): List<VehicleEntity>
}