package com.educa.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.DialogFragment
import com.educa.R
import com.educa.api.model.Topic
import com.educa.api.service.ApiClient


class FragmentModalDelete : DialogFragment() {

    lateinit var apiClient: ApiClient

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_modal_delete, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val btnClosePopUp: Button = view.findViewById(R.id.btn_back)
        val btn_deleteTopic: Button = view.findViewById(R.id.btn_deleteTopic)

        btn_deleteTopic.setOnClickListener {
            //deleteTopic(deletedTopic)
        }

        btnClosePopUp.setOnClickListener {
            dismiss()
        }

    }

    fun deleteTopic(deletedTopic: Topic) {
        apiClient.getMainApiService(requireActivity().applicationContext)
            .deleteTopic(deletedTopic.id)
        /* .enqueue(object : Callback<Topic> {
             override fun onResponse(
                 call: Call<Topic>,
                 response: Response<Topic>
             ) {
                 if (response.isSuccessful) {
                     val deletedTopic = response.body()
                     Log.w("Deleted Topic", "${deletedTopic}")

                 } else {

                     Log.e(
                         "ERRO AO DELETAR TÓPICO",
                         "Call: ${call} Response: ${response} Tópico DELETADO: ${deletedTopic}"
                     )
                 }
             }

             override fun onFailure(call: Call<Topic>, t: Throwable) {
                 t.printStackTrace()
                 Log.e(
                     "ERRO NO SERVIDOR AO DELETAR TÓPICO",
                     "Call: ${call}"
                 )
             }

         })

         */
    }
}