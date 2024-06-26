package com.example.ventas.models.database.dao

import androidx.room.*
import com.example.ventas.models.database.entities.ProductEntity
import com.example.ventas.models.database.entities.UserEntity

@Dao
interface UserDao {
    @Insert
    fun insertUser(user: UserEntity)

    @Delete
    fun deleteUser(user: UserEntity)

    @Update
    fun updateUser(user: UserEntity)

    @Query("SELECT * FROM tbUsuarios")
    fun getAllUsers(): List<UserEntity>

    @Query("SELECT * FROM tbUsuarios WHERE idUsuario = :userId")
    fun getUserById(userId: Int): UserEntity?

    @Query("SELECT * FROM tbUsuarios WHERE cedulaUsuario = :userCedula")
    fun getUserByCedula(userCedula: String): UserEntity?

    @Query("SELECT * FROM tbUsuarios WHERE cedulaUsuario = :username AND claveUsuario = :password")
    fun getUserByUsernameAndPassword(username: String, password: String): UserEntity?

    @Query("DELETE FROM tbUsuarios WHERE cedulaUsuario = :username")
    fun deleteUserByUsername(username: String)
}