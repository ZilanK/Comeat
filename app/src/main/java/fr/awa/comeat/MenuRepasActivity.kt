package fr.awa.comeat

import android.content.Intent
import android.os.Bundle
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

        val button3 : Button = findViewById(R.id.button3)
        button3.setOnClickListener {
            val intent = Intent(this, RepasActivity::class.java)
            startActivity(intent)

        }

        val button2 : Button = findViewById(R.id.button2)
        button2.setOnClickListener {
            val intent = Intent(this, RechecheRepasActivity::class.java)
            startActivity(intent)

        }


    }
}