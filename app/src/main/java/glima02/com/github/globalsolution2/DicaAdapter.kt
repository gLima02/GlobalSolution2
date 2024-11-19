package glima02.com.github.globalsolution2

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView

class DicaAdapter(private var dicas: List<Dica>) : RecyclerView.Adapter<DicaAdapter.DicaViewHolder>() {

    inner class DicaViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tituloDica: TextView = itemView.findViewById(R.id.tituloDica)
        val descricaoDica: TextView = itemView.findViewById(R.id.descricaoDica)

        fun bind(dica: Dica) {
            tituloDica.text = dica.titulo
            descricaoDica.text = dica.descricao
            itemView.setOnClickListener {
                // Exibe um Toast com a descrição completa
                Toast.makeText(itemView.context, dica.descricao, Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DicaViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_dica, parent, false)
        return DicaViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: DicaViewHolder, position: Int) {
        val dica = dicas[position]
        holder.bind(dica)
    }

    override fun getItemCount() = dicas.size

    fun updateData(newDicas: List<Dica>) {
        dicas = newDicas
        notifyDataSetChanged()
    }
}
