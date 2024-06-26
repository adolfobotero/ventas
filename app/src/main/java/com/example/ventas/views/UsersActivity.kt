package com.example.ventas.views

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.ventas.R
import com.example.ventas.databinding.ActivityUsersBinding
import com.example.ventas.models.Globals
import com.example.ventas.models.database.entities.UserEntity

class UsersActivity : AppCompatActivity(), View.OnClickListener {
        lateinit var binding: ActivityUsersBinding

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            binding = ActivityUsersBinding.inflate(layoutInflater)
            setContentView(binding.root)
            binding.btnCreateUser.setOnClickListener(this)
        }

        override fun onClick(v0: View?) {
            when (v0?.id) {
                R.id.btnCreateUser -> {
                    val idUser = binding.txtIdentificationCard.editText?.text.toString()
                    val nameUser = binding.txtFullName.editText?.text.toString()
                    val passUser = binding.txtNewPassword.editText?.text.toString()
                    val repeatPass = binding.txtRepeatPassword.editText?.text.toString()
                    val rolUser = binding.txtRole.editText?.text.toString()

                    if (idUser.isEmpty() || nameUser.isEmpty() || passUser.isEmpty() || repeatPass.isEmpty() || rolUser.isEmpty()) {
                        // Si alguno de los campos está vacío, mostrar un mensaje de error
                        Toast.makeText(
                            this,
                            "Por favor complete todos los campos",
                            Toast.LENGTH_SHORT
                        ).show()
                    } else {
                        // Verificar si ya existe un usuario con el mismo código
                        val existingUser =
                            Globals.getdataBase(this)?.userDao()?.getUserByCedula(idUser)
                        if (existingUser != null) {
                            // Si ya existe un usuario con el mismo código, mostrar un mensaje de error
                            Toast.makeText(
                                this,
                                "Ya existe un usuario con esta cédula",
                                Toast.LENGTH_SHORT
                            ).show()
                        } else {
                            // Todos los campos están OK
                            var userEntity = UserEntity()
                            userEntity.cedulaUsuario = binding.txtIdentificationCard.editText?.text.toString()
                            userEntity.nombreUsuario = binding.txtFullName.editText?.text.toString()

                            val newPassword = binding.txtNewPassword.editText?.text.toString()
                            val confirmPassword = binding.txtRepeatPassword.editText?.text.toString()

                            // Verificar que las contraseñas sean iguales y no estén vacías
                            if (newPassword.isNotEmpty() && newPassword == confirmPassword) {
                                userEntity.claveUsuario = newPassword

                                // Convertir la cadena rol a un entero
                                val rolString = binding.txtRole.editText?.text.toString()

                                // Verificar que el campo de rol no esté vacío y que contenga solo los valores permitidos
                                if (rolString.isNotEmpty() && (rolString == "1" || rolString == "2")) {
                                    userEntity.rolUsuario = rolString.toInt()

                                    // Aquí puedes continuar con el procesamiento del usuario
                                    Globals.getdataBase(this)?.userDao()?.insertUser(userEntity)
                                    Toast.makeText(
                                        this,
                                        "Se ha agregado un usuario",
                                        Toast.LENGTH_LONG
                                    )
                                        .show()
                                    clearFields()

                                    // Iniciar MainActivity solo después de agregar el usuario
                                    val intent = Intent(this, MainActivity::class.java)
                                    startActivity(intent)
                                } else {
                                    // Mostrar un mensaje de error al usuario indicando que el campo de rol es inválido
                                    Toast.makeText(
                                        this,
                                        "El campo de rol debe ser 1 o 2 y no puede estar vacío",
                                        Toast.LENGTH_LONG
                                    ).show()
                                }
                            } else {
                                // Mostrar un mensaje de error al usuario indicando que las contraseñas no coinciden
                                Toast.makeText(
                                    this,
                                    "Las contraseñas no coinciden o están vacías",
                                    Toast.LENGTH_LONG
                                ).show()

                            }
                        }
                    }
                }
            }
        }
    private fun clearFields() {
        binding.txtIdentificationCard.editText?.setText("")
        binding.txtFullName.editText?.setText("")
        binding.txtNewPassword.editText?.setText("")
        binding.txtRepeatPassword.editText?.setText("")
        binding.txtRole.editText?.setText("")
    }
}