package fr.awa.comeat

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import fr.awa.comeat.Modele.Modele

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        val saisieEmail : EditText = findViewById(R.id.email)
        val saisieMdp : EditText = findViewById(R.id.mdp)

        val boutonConnecter : Button = findViewById(R.id.connecter)

        boutonConnecter.setOnClickListener {
            val email : String = saisieEmail.text.toString()
            val mdp : String = saisieMdp.text.toString()

            Log.d("ACT_CONN", "Connexion : $email/$mdp")

            val utilisateur = Modele.findUtilisateur(email, mdp)

            if (utilisateur != null) {
                // ✅ Authentification réussie
                Session.ouvrir(utilisateur.prenom + " " + utilisateur.nom)
                Toast.makeText(this, "Bienvenue ${utilisateur.prenom} !", Toast.LENGTH_SHORT).show()
                Log.d("ACT_CONN", "Connexion réussie : ${utilisateur.email}")

                val intent = Intent(this, MenuRepasActivity::class.java)
                startActivity(intent)
            } else {
                // ❌ Échec
                Toast.makeText(this, "Email ou mot de passe incorrect", Toast.LENGTH_SHORT).show()
                Log.d("ACT_CONN", "Échec de connexion")
            }
        }

        val boutonAnnuler : Button = findViewById(R.id.annuler)
        boutonAnnuler.setOnClickListener {
            saisieEmail.setText("")
            saisieMdp.setText("")
            Log.d("ACT_CONN", "Annulation")
        }
    }
}