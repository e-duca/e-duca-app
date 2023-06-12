package com.educa.ui.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.educa.R
import com.educa.api.model.AnswerResponse
import com.educa.ui.recyclerview.RecyclerViewInterface

class AnswerListAdapter(
    private val context: Context,
    private val answers: List<AnswerResponse>?,
    private val recyclerViewInterface: RecyclerViewInterface
) : RecyclerView.Adapter<AnswerListAdapter.ViewHolder>() {

    class ViewHolder(view: View, recyclerViewInterface: RecyclerViewInterface) :
        RecyclerView.ViewHolder(view) {
        fun bind(answer: AnswerResponse, recyclerViewInterface: RecyclerViewInterface) {
            val title = itemView.findViewById<TextView>(R.id.answerBody)
            title.text = answer.resposta

            //val posted = itemView.findViewById<TextView>(R.id.postedAt)
            //posted.text = answer.dataCriacao

            itemView.setOnClickListener(View.OnClickListener {
                if (true) {
                    val position = adapterPosition
                    if (position != RecyclerView.NO_POSITION) {
                        recyclerViewInterface.onItemClick(position)
                    }
                }
            })
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AnswerListAdapter.ViewHolder {
        val inflater = LayoutInflater.from(context)
        val view = inflater.inflate(R.layout.fragment_answer, parent, false)
        return AnswerListAdapter.ViewHolder(view, recyclerViewInterface)
    }

    override fun onBindViewHolder(holder: AnswerListAdapter.ViewHolder, position: Int) {
        val answer = answers?.get(position)
        holder.bind(answer!!, recyclerViewInterface)
    }

    override fun getItemCount(): Int = answers!!.size
}