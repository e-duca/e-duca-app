package com.educa.ui.recyclerview.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.educa.R
import com.educa.api.model.ContentResponse

class ContentListAdapter(
    private val context: Context,
    private val contents: MutableList<ContentResponse>,
    private val recyclerViewInterface: RecyclerViewInterface
) : RecyclerView.Adapter<ContentListAdapter.ViewHolder>() {

    class ViewHolder(view: View, recyclerViewInterface: RecyclerViewInterface) : RecyclerView.ViewHolder(view) {
        fun bind(content: ContentResponse, recyclerViewInterface: RecyclerViewInterface) {
            val title = itemView.findViewById<TextView>(R.id.title)
            title.text = content.titulo

            val ability = itemView.findViewById<TextView>(R.id.ability)
            ability.text = content.habilidade.codigo

            val posted = itemView.findViewById<TextView>(R.id.postedAt)
            posted.text = content.dataCriacao

            itemView.setOnClickListener(View.OnClickListener {
                if (true) {
                    val position = adapterPosition
                     if (position != RecyclerView.NO_POSITION){
                         recyclerViewInterface.onItemClick(position)
                     }
                }
            })
        }
    }

    // Responsável pelo bind das views, sempre retorna o viewHolder criado
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(context)
        val view = inflater.inflate(R.layout.fragment_card_content, parent, false)
        return ViewHolder(view, recyclerViewInterface)
    }

    // Indica qual view está no momento
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val content = contents[position]
        holder.bind(content, recyclerViewInterface)

    }

    // Determina quantos itens estarão dentro
    override fun getItemCount(): Int = contents.size


}
