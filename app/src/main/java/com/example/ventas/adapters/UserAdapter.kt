package com.example.ventas.adapters

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.example.ventas.R
import com.example.ventas.models.Users

class UserAdapter: BaseAdapter {
    var context: Context
    var users: Users

    constructor(context : Context, users: Users){
        this.context = context
        this.users = users
    }
    override fun getCount(): Int {
        return users.users.size
    }
    override fun getItem(p0: Int): Any {
        return users.users[p0]
    }
    override fun getItemId(p0: Int): Long {
        return p0.toLong()
    }
    override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View? {
        var view : View? = p1
        if (view == null){
            view = View.inflate(context, R.layout.adapter_list_users, null)
        }
        var user = users.users[p0]

        var txtIdUser = view?.findViewById<TextView>(R.id.viewIdUser)
        txtIdUser?.text = user.idUsuario.toString()
        var txtCedUser = view?.findViewById<TextView>(R.id.viewCedUser)
        txtCedUser?.text = user.cedulaUsuario
        var txtNameUser = view?.findViewById<TextView>(R.id.viewNameUser)
        txtNameUser?.text = user.nombreUsuario
        val txtRoleUser = view?.findViewById<TextView>(R.id.viewRoleUser)
        txtRoleUser?.text = when (user.rolUsuario) {
            1 -> "Administrador"
            2 -> "Vendedor"
            else -> "Rol desconocido"
        }
        return view
    }
}