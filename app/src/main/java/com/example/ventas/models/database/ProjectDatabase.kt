package com.example.ventas.models.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.ventas.models.database.dao.CustomerDao
import com.example.ventas.models.database.dao.ProductDao
import com.example.ventas.models.database.dao.SaleDao
import com.example.ventas.models.database.dao.SaleDetailDao
import com.example.ventas.models.database.dao.SupplieDao
import com.example.ventas.models.database.dao.UserDao
import com.example.ventas.models.database.dao.VehicleDao
import com.example.ventas.models.database.entities.CustomerEntity
import com.example.ventas.models.database.entities.ProductEntity
import com.example.ventas.models.database.entities.SaleDetailEntity
import com.example.ventas.models.database.entities.SaleEntity
import com.example.ventas.models.database.entities.SupplieEntity
import com.example.ventas.models.database.entities.UserEntity
import com.example.ventas.models.database.entities.VehicleEntity

//@Database(entities = arrayOf(UserEntity::class), version = 1)
@Database(entities = [
    UserEntity::class,
    CustomerEntity::class,
    SaleEntity::class,
    SaleDetailEntity::class,
    ProductEntity::class,
    VehicleEntity::class,
    SupplieEntity::class], version = 2)

@TypeConverters(LocalDateConverter::class)
abstract class ProjectDatabase : RoomDatabase(){
    abstract fun userDao(): UserDao
    abstract fun customerDao(): CustomerDao
    abstract fun saleDao(): SaleDao
    abstract fun saleDetailDao(): SaleDetailDao
    abstract fun productDao(): ProductDao
    abstract fun vehicleDao(): VehicleDao
    abstract fun supplieDao(): SupplieDao
}