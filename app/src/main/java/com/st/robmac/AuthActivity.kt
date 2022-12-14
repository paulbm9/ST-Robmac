package com.st.robmac

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AlertDialog
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_auth.*

class AuthActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth)

        setup()


    }

    private fun setup() {
        title = "Autenticacion"
        signUp.setOnClickListener {
            if (ETemail.text.isNotEmpty() && ETpassword.text.isNotEmpty()) {
                FirebaseAuth.getInstance().createUserWithEmailAndPassword(
                    ETemail.text.toString(), ETpassword.text.toString()
                ).addOnCompleteListener {
                    if (it.isSuccessful) {
                        pbmain.setVisibility(View.VISIBLE)
                        showHome(it.result?.user?.email ?: "", ProviderType.BASIC)
                    } else {
                        showAlert()
                    }
                }
            }
        }
        signIn.setOnClickListener {
            if (ETemail.text.isNotEmpty() && ETpassword.text.isNotEmpty()) {
                FirebaseAuth.getInstance().signInWithEmailAndPassword(
                    ETemail.text.toString(), ETpassword.text.toString()
                ).addOnCompleteListener {
                    if (it.isSuccessful) {
                        pbmain.setVisibility(View.VISIBLE)
                        showHome(it.result?.user?.email ?: "", ProviderType.BASIC)
                    } else {
                        showAlert()
                    }
                }
            }
        }
    }

    private fun showAlert() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Error")
        builder.setMessage("Se ha producido un error autenticando al usuario")
        builder.setPositiveButton("Aceptar", null)
        val dialog: AlertDialog = builder.create()
        dialog.show()
    }

    private fun showHome(email: String, provider: ProviderType) {
        val homeIntent = Intent(this, HomeActivity::class.java).apply {
            putExtra("email", email)
            putExtra("provider", provider.name)
        }
        startActivity(homeIntent)
    }

    override fun onResume() {
        super.onResume()
        pbmain.setVisibility(View.GONE)
    }

}