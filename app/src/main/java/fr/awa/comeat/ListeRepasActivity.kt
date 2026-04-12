package fr.awa.comeat

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import fr.awa.comeat.Modele.Modele
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class ListeRepasActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_liste_repas)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        findViewById<android.widget.Button>(R.id.buttonRetour).setOnClickListener {
            finish()
        }

        val idUtilisateur = intent.getIntExtra("id_utilisateur", -1)
        val specialite = intent.getStringExtra("specialite") ?: ""
        val dateStr = intent.getStringExtra("date_repas") ?: ""
        val date = LocalDate.parse(dateStr, DateTimeFormatter.ofPattern("dd/MM/yyyy"))

        // RecyclerView
        val rvRepas = findViewById<RecyclerView>(R.id.rv_repas_proposes)
        rvRepas.layoutManager = LinearLayoutManager(this)

        val adapterRepas = RepasProposesAdapter(
            Modele.getRepasByDateSpecialite(specialite, date, idUtilisateur)
        ) { repas ->

            val intent = Intent(this, VisuRepasActivity::class.java)
            intent.putExtra("id_utilisateur", idUtilisateur)
            intent.putExtra("id_repas", repas.id)
            startActivity(intent)
        }

        rvRepas.adapter = adapterRepas
    }
}