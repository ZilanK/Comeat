package fr.awa.comeat

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

data class Utilisateur(
    val id: Int,
    val nom: String,
    val prenom: String,
    val email: String,
    val mdp: String)