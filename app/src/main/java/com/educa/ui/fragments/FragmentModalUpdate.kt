package com.educa.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.DialogFragment
import com.educa.R
import com.educa.api.model.Topic
import com.educa.api.service.ApiClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class FragmentModalUpdate : DialogFragment() {

    lateinit var apiClient: ApiClient

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_modal_update, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        apiClient = ApiClient()

        val btnClosePopUp: Button = view.findViewById(R.id.btn_back)
        val btn_updateTopic = view.findViewById<Button>(R.id.btn_updateTopic)

        btn_updateTopic.setOnClickListener {
            val subjectField = view.findViewById<EditText>(R.id.ipt_titleContent)
            val subject = subjectField.text.toString()

            val descriptionField = view.findViewById<EditText>(R.id.ipt_topicBody)
            val description = descriptionField.text.toString()

            if (subject.isNotBlank() &&
                description.isNotBlank()
            ) {
                val updatedTopic = Topic(
                    id,
                    titulo = subject,
                    descricao = description
                )
                updateTopicInfo(updatedTopic)
            } else {
                Log.e("ERRO", "Os campos não podem estar vazios")
            }
        }

        btnClosePopUp.setOnClickListener {
            dismiss()
        }

    }

    fun updateTopicInfo(updatedTopic: Topic) {
        apiClient.getMainApiService(
            requireActivity().applicationContext
        ).updateTopic(updatedTopic.id, updatedTopic)
            .enqueue(object : Callback<Topic> {
                override fun onResponse(
                    call: Call<Topic>,
                    response: Response<Topic>
                ) {
                    if (response.isSuccessful) {
                        val updatedTopic = response.body()
                        Log.w("Updated Topic", "${updatedTopic}")

                    } else {
                        Log.e(
                            "ERRO AO ATULIZAR TÓPICO",
                            "Call: ${call} Response: ${response} TópicoErro no else: ${updatedTopic}"
                        )
                    }
                }

                override fun onFailure(call: Call<Topic>, t: Throwable) {
                    t.printStackTrace()
                    Log.e(
                        "ERRO NO SERVIDOR AO ATULIZAR TÓPICO",
                        "Call: ${call}"
                    )
                }
            })
    }
}