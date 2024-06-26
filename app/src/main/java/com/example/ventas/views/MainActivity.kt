package com.example.ventas.views

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.ventas.R
import com.example.ventas.databinding.ActivityMainBinding
import com.example.ventas.models.Globals
import com.example.ventas.models.database.ProjectDatabase

class MainActivity : AppCompatActivity(), View.OnClickListener {
    lateinit var binding: ActivityMainBinding
    lateinit var projectDatabase: ProjectDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        Globals.initDatabase(this)

        binding.btnSignUp.setOnClickListener(this)
        binding.btnLogin.setOnClickListener(this)
        projectDatabase = Globals.getdataBase(this)!!

        // Verificar si hay un usuario almacenado en SharedPreferences
        val userCode = Globals.getUserCode(this)
        if (userCode != null) {
            // Si hay un nombre de usuario almacenado, iniciar sesión automáticamente
            loginAutomatically(userCode)
        }
    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.btnLogin -> {
                val username = binding.txtUser.editText?.text.toString()
                val password = binding.txtPassword.editText?.text.toString()
                login(username, password)
            }
            R.id.btnSignUp -> {
                val intent = Intent(this, UsersActivity::class.java)
                startActivity(intent)
            }
        }
    }
    private fun login(username: String, password: String) {
        val userDao = projectDatabase.userDao()
        val user = userDao.getUserByUsernameAndPassword(username, password)
        if (user != null) {
            // Guardar el nombre de usuario y el ID en SharedPreferences
            Globals.saveUserCode(this, user.cedulaUsuario)
            Globals.saveUserName(this, user.nombreUsuario)
            Globals.saveUserRole(this, user.rolUsuario.toString())
            Globals.saveUserId(this, user.idUsuario)

            // Verificar el rol del usuario
            if (user.rolUsuario == 1) {
                // El usuario tiene el rol 1, mostrar el botón btnListUsuarios
                val intent = Intent(this, MenuActivity::class.java)
                intent.putExtra("username", user.cedulaUsuario)
                intent.putExtra("showUserListButton", true)
                startActivity(intent)
            } else {
                // El usuario no tiene el rol 1, ocultar el botón btnListUsuarios
                val intent = Intent(this, MenuActivity::class.java)
                intent.putExtra("username", user.cedulaUsuario)
                intent.putExtra("showUserListButton", false)
                startActivity(intent)
            }
        } else {
            // Credenciales incorrectas, mostrar mensaje de error
            Toast.makeText(this, "Credenciales incorrectas", Toast.LENGTH_SHORT).show()
        }
    }
    private fun loginAutomatically(username: String) {
        // Iniciar sesión automáticamente utilizando el nombre de usuario almacenado
        startActivity(Intent(this, MenuActivity::class.java))
        finish()
    }
}