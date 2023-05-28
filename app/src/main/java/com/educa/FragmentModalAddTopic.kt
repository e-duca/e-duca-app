package com.educa

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import com.educa.api.model.Topic
import com.educa.api.service.ApiClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class FragmentModalAddTopic : DialogFragment() {

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

        val btn_addTopic = view.findViewById<Button>(R.id.btn_addTopic)

        btn_addTopic.setOnClickListener {
            val subjectField = view.findViewById<EditText>(R.id.ipt_name)
            val subject = subjectField.text.toString()

            val descriptionField = view.findViewById<EditText>(R.id.ipt_topicBody)
            val description = descriptionField.text.toString()

            if (subject.isNotBlank() &&
                description.isNotBlank()
            ) {
                val newTopic = Topic(
                    id,
                    titulo = subject,
                    descricao = description
                )
                addNewTopic(newTopic)
            } else {
                Log.e("ERRO", "Os campos não podem estar vazios")
            }
        }

        val btnClosePopUp: Button = view.findViewById(R.id.btn_back)

        btnClosePopUp.setOnClickListener {
            dismiss()
        }
    }

    fun addNewTopic(newTopic: Topic) {
        apiClient.getMainApiService().registerTopic(newTopic)
            .enqueue(object : Callback<Topic> {
                override fun onResponse(
                    call: Call<Topic>,
                    response: Response<Topic>
                ) {
                    if (response.isSuccessful) {
                        val topic = response.body()
                        Log.w("newTopic", "${newTopic}")

                    } else {

                        Log.e(
                            "ERRO AO CRIAR NOVO TÓPICO",
                            "Call: ${call} Response: ${response} NewStudent: ${newTopic}"
                        )
                    }
                }

                override fun onFailure(call: Call<Topic>, t: Throwable) {
                    t.printStackTrace()
                    Log.e(
                        "ERRO NO SERVIDOR AO CADASTRAR NOVO TÓPICO",
                        "Call: ${call}"
                    )
                }

            })
    }

}