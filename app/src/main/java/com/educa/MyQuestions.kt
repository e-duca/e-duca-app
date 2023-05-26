package com.educa

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.educa.api.model.Content
import com.educa.api.model.Student
import com.educa.api.model.Topic
import com.educa.api.service.ApiClient
import com.educa.ui.recyclerview.adapter.ContentListAdapter
import com.educa.ui.recyclerview.adapter.TopicListAdapter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MyQuestions : AppCompatActivity() {
    lateinit var myTopics: RecyclerView
    private lateinit var apiClient: ApiClient
    lateinit var topicAdapter: TopicListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_questions)

        apiClient = ApiClient()
        myTopics = findViewById<RecyclerView>(R.id.rv_topics)

        val myTopicsList = mutableListOf<Topic>()

        topicAdapter = TopicListAdapter(this, myTopicsList)

        fun loadMyTopicsList() {
            apiClient.getMainApiService().getTopic().enqueue(object : Callback<List<Topic>> {
                override fun onResponse(
                    call: Call<List<Topic>>,
                    response: Response<List<Topic>>
                ) {
                    if (response.isSuccessful && !response.body()!!.isNullOrEmpty()) {
                        myTopicsList.addAll(response.body()!!)
                        topicAdapter.notifyDataSetChanged()

                        Log.i(
                            "Entrou no is sucessful",
                            "Call: ${call}"
                        )
                    }
                }

                override fun onFailure(call: Call<List<Topic>>, t: Throwable) {
                    t.printStackTrace()
                    Toast.makeText(
                        this@MyQuestions, t.message,
                        Toast.LENGTH_SHORT
                    ).show()

                    Log.e(
                        "ERRO AO PUXAR CONTEUDO",
                        "Call: ${call}"
                    )
                }

            })
        }
        loadMyTopicsList()

    }

    fun loadAllTopicsList() {
        val allTopicsList = mutableListOf<Topic>()

        apiClient.getMainApiService().getAllTopics().enqueue(object : Callback<List<Topic>> {
            override fun onResponse(
                call: Call<List<Topic>>,
                response: Response<List<Topic>>
            ) {
                if (response.isSuccessful && !response.body()!!.isNullOrEmpty()) {
                    allTopicsList.addAll(response.body()!!)
                    topicAdapter.notifyDataSetChanged()

                    Log.i(
                        "PUXOU TODOS OS TOPICOS COM SUCESSO",
                        "Call: ${call}"
                    )
                }
            }

            override fun onFailure(call: Call<List<Topic>>, t: Throwable) {
                t.printStackTrace()
                Toast.makeText(
                    this@MyQuestions, t.message,
                    Toast.LENGTH_SHORT
                ).show()

                Log.e(
                    "ERRO AO PUXAR TODOS OS TOPICOS",
                    "Call: ${call}"
                )
            }
        })
    }
}