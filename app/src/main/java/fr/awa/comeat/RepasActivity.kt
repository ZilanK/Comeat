package fr.awa.comeat

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import fr.awa.comeat.Modele.Modele

class RepasActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_repas)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val rvRepas = findViewById<RecyclerView>( R.id.rv_repas)


        rvRepas.layoutManager = LinearLayoutManager( this )

        findViewById<android.widget.Button>(R.id.buttonRetour).setOnClickListener {
            finish()
        }

        val idUtilisateur = intent.getIntExtra("id_utilisateur", -1)
        val sesRepas = Modele.getSesRepas(idUtilisateur)
        rvRepas.adapter = RepasAdapter(sesRepas)


    }
}