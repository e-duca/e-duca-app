package com.educa

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.fragment.app.FragmentContainerView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.educa.api.model.*
import com.educa.api.service.ApiClient
import com.educa.api.service.SessionManager
import com.educa.ui.adapters.TopicListAdapter
import com.educa.ui.recyclerview.RecyclerViewInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MyQuestions : AppCompatActivity(), RecyclerViewInterface {
    lateinit var myTopics: RecyclerView
    private lateinit var apiClient: ApiClient
    private lateinit var sessionManager: SessionManager
    lateinit var topicAdapter: TopicListAdapter
    lateinit var myTopicsList: MutableList<TopicResponse>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_questions)

        apiClient = ApiClient()
        sessionManager = SessionManager(this)
        myTopics = findViewById<RecyclerView>(R.id.rv_topics)!!

        loadMyTopicsList()
        Log.i(
            "TÓPICO: CHAMOU A FUNÇÃO",
            "loadMyTopicsList"
        )

        val seeAll = findViewById<FragmentContainerView>(R.id.fg_btn_seeAlltopics)
        seeAll.setOnClickListener{
            val allTopicsPage = Intent(applicationContext, AllQuestions::class.java)
            allTopicsPage.putExtra("btn_text", "Ver meus tópicos")
            startActivity(allTopicsPage)
        }
    }

    fun loadMyTopicsList() {
        myTopicsList = mutableListOf()
        topicAdapter = TopicListAdapter(this, myTopicsList, this)

        val layoutManager = LinearLayoutManager(this)

        myTopics.layoutManager = layoutManager

        myTopics.adapter = topicAdapter

        apiClient.getMainApiService(this)
            .getMyTopics()
            .enqueue(object : Callback<TopicResponseArray> {
                @SuppressLint("NotifyDataSetChanged")
                override fun onResponse(
                    call: Call<TopicResponseArray>,
                    response: Response<TopicResponseArray>
                ) {
                    if (response.isSuccessful) {
                        response.body()?.topic?.let { myTopicsList.addAll(it) }
                        topicAdapter.notifyDataSetChanged()

                        Log.i(
                            "TÓPICO: ENTROU NO ISSUCCESSFUL",
                            "Call: ${call} Response: ${response.body()} ${response} ${myTopicsList}"
                        )

                    } else {
                        Log.i(
                            "TÓPICO: ENTROU NO IF DO ISSUCCESSFUL MAS CAIU NO ELSE",
                            "Call: ${call}, Response: ${response.code()} ${response.body()})"
                        )
                    }
                }

                override fun onFailure(call: Call<TopicResponseArray>, t: Throwable) {
                    t.printStackTrace()
                    Toast.makeText(
                        this@MyQuestions, t.message,
                        Toast.LENGTH_SHORT
                    ).show()

                    Log.e(
                        "ERRO AO PUXAR TÓPICO",
                        "Call: ${call}  ${t.message} ${t.printStackTrace()}"
                    )
                }
            })
    }


    override fun onItemClick(position: Int) {
        val accessTopic = Intent(this.applicationContext, AccessThread::class.java)

        accessTopic.putExtra("title", myTopicsList[position].titulo)
        accessTopic.putExtra("txt_postedAt", myTopicsList[position].dataCriacao)
        accessTopic.putExtra("txt_nameStudent", myTopicsList[position].usuario.nome)
        startActivity(accessTopic)
    }

}