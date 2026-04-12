package fr.awa.comeat

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import fr.awa.comeat.Modele.Repas

class RepasProposesAdapter(
    private val lesRepas: List<Repas>,
    private val onItemClick: (Repas) -> Unit
) : RecyclerView.Adapter<RepasProposesAdapter.RepasViewHolder>() {

    class RepasViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        val tvIdRepas: TextView = view.findViewById(R.id.tv_id_repas)
        val tvHote: TextView = view.findViewById(R.id.tv_hote)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepasViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.item_repas_propose,
            parent,
            false
        )
        return RepasViewHolder(view)
    }

    override fun onBindViewHolder(holder: RepasViewHolder, position: Int) {
        val repas = lesRepas[position]
        holder.tvIdRepas.text = repas.id.toString()
        holder.tvHote.text = repas.hote.nom + " " + repas.hote.prenom

        holder.view.setOnClickListener {
            onItemClick(repas)
        }
    }

    override fun getItemCount(): Int {
        return lesRepas.size
    }
}