package com.st.robmac

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_auth.*
import kotlinx.android.synthetic.main.activity_home.*

enum class ProviderType {
    BASIC
}

class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        //setup
        val bundle: Bundle? = intent.extras
        val email: String? = bundle?.getString("email")
        val provider: String? = bundle?.getString("provider")
        setup(email ?: "", provider ?: "")

        swapMaps()
        swapRegister()

    }


    private fun showMaps() {
        val mapsIntent = Intent(this, MapsActivity::class.java).apply {}
        startActivity(mapsIntent)

    }
    private fun showRegister(){
        val regIntent = Intent(this,ReportActivity::class.java).apply{}
        startActivity(regIntent)
    }

    private fun setup(email: String, provider: String) {
        title = "Inicio"
        tvEmailH.text = email
        tvProveedorH.text = provider
        logOut.setOnClickListener {
            FirebaseAuth.getInstance().signOut()
            onBackPressed()
        }
    }

    private fun swapMaps() {
        Reportar.setOnClickListener { showMaps() }
    }

    private fun swapRegister(){
        Registrar.setOnClickListener { showRegister() }
    }

}