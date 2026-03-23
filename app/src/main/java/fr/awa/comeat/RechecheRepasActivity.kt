package fr.awa.comeat

import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import fr.awa.comeat.Modele.Modele
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import android.widget.Toast

class RechecheRepasActivity : AppCompatActivity() {

    private var libelleSpecialiteCulinaire: String = ""
    private var date_repas: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_recheche_repas)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Spinner spécialités
        val specialites = Modele.getSpecialites()
        val spnSpecialiteCulinaire: Spinner = findViewById(R.id.select_specialite)
        val adaptateur = ArrayAdapter(this, android.R.layout.simple_spinner_item, specialites)
        adaptateur.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spnSpecialiteCulinaire.adapter = adaptateur

        spnSpecialiteCulinaire.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parentView: AdapterView<*>,
                    selectedItemView: View?,
                    position: Int,
                    id: Long
                ) {
                    libelleSpecialiteCulinaire = specialites[position].libelle
                }

                override fun onNothingSelected(parentView: AdapterView<*>) {
                    // Obligatoire mais on ne fait rien ici
                }
            }

        // Bouton date
        val btnDate: Button = findViewById(R.id.select_date)
        val tvDate: TextView = findViewById(R.id.aff_date)

        btnDate.setOnClickListener {
            val dateCourante = LocalDate.now()
            val annee = dateCourante.year
            val mois = dateCourante.monthValue - 1
            val jour = dateCourante.dayOfMonth

            val datePickerDialog = DatePickerDialog(
                this,
                { _, anneeSelect, moisSelect, jourSelect ->
                    val dateSelectionnee = LocalDate.of(anneeSelect, moisSelect + 1, jourSelect)
                    val formateur = DateTimeFormatter.ofPattern("dd/MM/yyyy")
                    date_repas = dateSelectionnee.format(formateur)
                    tvDate.text = date_repas
                },
                annee, mois, jour
            )
            datePickerDialog.show()
        }

        // Bouton valider
        val btnValider: Button = findViewById(R.id.btnvalider)
        btnValider.setOnClickListener {
            if (date_repas.isEmpty()) {
                Toast.makeText(this, "Veuillez sélectionner une date", Toast.LENGTH_SHORT).show()
            } else {
                val intent = Intent(this, ListeRepasActivity::class.java)
                startActivity(intent)
            }
        }

    } // ferme onCreate
} // ferme la classe