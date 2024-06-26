package com.example.ventas.views

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.ventas.R
import com.example.ventas.adapters.UserAdapter
import com.example.ventas.databinding.ActivityListUsersBinding
import com.example.ventas.models.Globals
import com.example.ventas.models.Users
import com.example.ventas.models.database.entities.UserEntity

class ListUsersActivity : AppCompatActivity(), View.OnClickListener {

    lateinit var binding: ActivityListUsersBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListUsersBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.viewReturnLstUsersReg.setOnClickListener(this)

        var users : Users = Users(Globals.getdataBase(this)?.userDao()?.getAllUsers()!! as ArrayList<UserEntity>)
        var adapter : UserAdapter = UserAdapter(this, users)

        binding.lstUsersReg.adapter = adapter
        Toast.makeText(this, "Total usuarios registrados: ${users.users.size}", Toast.LENGTH_LONG).show()
    }
    override fun onClick(v0: View?) {
        when (v0?.id) {
            R.id.viewReturnLstUsersReg -> {
                val intent = Intent(this, MenuActivity::class.java)
                startActivity(intent)
            }
        }
    }
}