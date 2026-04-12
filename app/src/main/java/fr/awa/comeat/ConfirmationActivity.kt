package fr.awa.comeat

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import fr.awa.comeat.Modele.Modele

class ConfirmationActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_confirmation)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val idUtilisateur = intent.getIntExtra("id_utilisateur", -1)

        findViewById<android.widget.Button>(R.id.buttonRetour).setOnClickListener {
            val intent = Intent(this, RepasActivity::class.java)
            intent.putExtra("id_utilisateur", idUtilisateur)
            startActivity(intent)
        }
        val idRepas = intent.getIntExtra("id_repas", -1)

        val succes = Modele.inscrire(idRepas, idUtilisateur)

        val tvConfirmation = findViewById<TextView>(R.id.tv_confirmation)
        if (succes) {
            tvConfirmation.text = "Vous êtes bien inscrit au repas !"
        } else {
            tvConfirmation.text = "Inscription impossible."
        }
    }
}