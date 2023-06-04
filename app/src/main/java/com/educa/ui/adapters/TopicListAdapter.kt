package com.educa.ui.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.educa.R
import com.educa.api.model.Topic

class TopicListAdapter(
    private val context: Context,
    private val topics: MutableList<Topic>
) : RecyclerView.Adapter<TopicListAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bind(topic: Topic) {
            val subject = itemView.findViewById<TextView>(R.id.title)
            subject.text = topic.titulo

            val description = itemView.findViewById<TextView>(R.id.description)
            description.text = topic.descricao
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(context)
        val view = inflater.inflate(R.layout.fragment_card_topic, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val topic = topics[position]
        holder.bind(topic)
    }

    override fun getItemCount(): Int = topics.size
}
