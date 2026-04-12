package fr.awa.comeat

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MenuRepasActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_menu_repas)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val idUtilisateur = intent.getIntExtra("id_utilisateur", -1)
        Log.d("CHAIN", "MenuRepasActivity reçoit id = $idUtilisateur")

        val buttonMesParticipations : Button = findViewById(R.id.buttonMesParticipations)
        buttonMesParticipations.setOnClickListener {
            val intent = Intent(this, RepasActivity::class.java)
            intent.putExtra("id_utilisateur", idUtilisateur)
            Log.d("CHAIN", "MenuRepasActivity envoie id = $idUtilisateur vers RepasActivity")
            startActivity(intent)
        }

        val buttonRepasProposes : Button = findViewById(R.id.buttonRepasProposes)
        buttonRepasProposes.setOnClickListener {
            val intent = Intent(this, RechecheRepasActivity::class.java)
            intent.putExtra("id_utilisateur", idUtilisateur)
            startActivity(intent)
        }
    }
}
