package com.educa.ui.recyclerview.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.educa.R
import com.educa.api.model.Content

class ContentListAdapter(
    private val context: Context,
    private val contents: MutableList<Content>
) : RecyclerView.Adapter<ContentListAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bind(content: Content) {
            val title = itemView.findViewById<TextView>(R.id.title)
            title.text = content.titulo

            val hability = itemView.findViewById<TextView>(R.id.hability)
            hability.text = content.habilidade.codigo
        }
    }

    // Responsável pelo bind das views, sempre retorna o viewHolder criado
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(context)
        val view = inflater.inflate(R.layout.fragment_card_content, parent, false)
        return ViewHolder(view)
    }

    // Indica qual view está no momento
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val content = contents[position]
        holder.bind(content)

    }

    // Determina quantos itens estarão dentro
    override fun getItemCount(): Int = contents.size


}
