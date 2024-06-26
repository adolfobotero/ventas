package com.example.ventas.models

import android.content.Context
import androidx.room.Room
import com.example.ventas.models.database.ProjectDatabase

class Globals {
    companion object {
        var dataBase : ProjectDatabase ?= null


        fun saveUserCode(context: Context, userCode: String) {
            val sharedPreferences = context.getSharedPreferences("APP", Context.MODE_PRIVATE)
            val editor = sharedPreferences.edit()
            editor.putString("userCode", userCode)
            editor.apply()
        }

        fun saveUserRole(context: Context, userRole: String) {
            val sharedPreferences = context.getSharedPreferences("APP", Context.MODE_PRIVATE)
            val editor = sharedPreferences.edit()
            editor.putString("userRole", userRole)
            editor.apply()
        }

        fun saveUserId(context: Context, userId: Int) {
            val sharedPreferences = context.getSharedPreferences("APP", Context.MODE_PRIVATE)
            val editor = sharedPreferences.edit()
            editor.putInt("userId", userId)
            editor.apply()
        }

        fun saveUserName(context: Context, userName: String) {
            val sharedPreferences = context.getSharedPreferences("APP", Context.MODE_PRIVATE)
            val editor = sharedPreferences.edit()
            editor.putString("userName", userName)
            editor.apply()
        }

        fun getUserCode(context: Context): String? {
            val sharedPreferences = context.getSharedPreferences("APP", Context.MODE_PRIVATE)
            return sharedPreferences.getString("userCode", null)
        }

        fun getUserId(context: Context): Int {
            val sharedPreferences = context.getSharedPreferences("APP", Context.MODE_PRIVATE)
            return sharedPreferences.getInt("userId", -1)
        }

        fun getUserRole(context: Context): String? {
            val sharedPreferences = context.getSharedPreferences("APP", Context.MODE_PRIVATE)
            return sharedPreferences.getString("userRole", null)
        }

        fun getUserName(context: Context): String? {
            val sharedPreferences = context.getSharedPreferences("APP", Context.MODE_PRIVATE)
            return sharedPreferences.getString("userName", null)
        }

        fun clearUserCode(context: Context) {
            val sharedPreferences = context.getSharedPreferences("APP", Context.MODE_PRIVATE)
            val editor = sharedPreferences.edit()
            editor.remove("userCode")
            editor.apply()
        }

        fun getdataBase(context : Context): ProjectDatabase? {
            if (dataBase == null) {
                initDatabase(context)
            }
            return dataBase
        }

        fun initDatabase(context: Context){
            dataBase = Room.databaseBuilder(context.applicationContext, ProjectDatabase::class.java, "project_database")
                .fallbackToDestructiveMigration()
                .allowMainThreadQueries().build()
        }
    }
}