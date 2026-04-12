package fr.awa.comeat

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import fr.awa.comeat.Modele.Modele

class VisuRepasActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_visu_repas)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        findViewById<android.widget.Button>(R.id.buttonRetour).setOnClickListener {
            finish()
        }

        val idUtilisateur = intent.getIntExtra("id_utilisateur", -1)
        val idRepas = intent.getIntExtra("id_repas", -1)

        val repas = Modele.getRepasById(idRepas)

        if (repas != null) {
            findViewById<TextView>(R.id.tv_date).text = repas.date.toString()
            findViewById<TextView>(R.id.tv_specialite).text = repas.specialite.libelle
            findViewById<TextView>(R.id.tv_hote).text = repas.hote.nom
            findViewById<TextView>(R.id.tv_places).text = "Places libres : ${repas.getNbPlacesLibres()}"
        }

        val buttonParticiper: Button = findViewById(R.id.buttonParticiper)
        buttonParticiper.setOnClickListener {
            val intent = Intent(this, ConfirmationActivity::class.java)
            intent.putExtra("id_utilisateur", idUtilisateur)
            intent.putExtra("id_repas", idRepas)
            startActivity(intent)
        }
    }
}
