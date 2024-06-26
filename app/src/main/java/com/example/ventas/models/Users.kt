package com.example.ventas.models

import com.example.ventas.models.database.entities.UserEntity

class Users {
    var users = ArrayList<UserEntity>()

    constructor(users: ArrayList<UserEntity>){
        this.users = users
    }
}