package com.educa

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.educa.api.model.Answer
import com.educa.api.model.TopicResponseArray
import com.educa.api.service.ApiClient
import com.educa.ui.adapters.AnswerListAdapter
import com.educa.ui.recyclerview.RecyclerViewInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AccessThread : AppCompatActivity(), RecyclerViewInterface {
    lateinit var newAnswer: Answer
    lateinit var apiClient: ApiClient
    lateinit var answers: RecyclerView
    lateinit var answerAdapter: AnswerListAdapter
    lateinit var page: Intent

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_access_thread)
        apiClient = ApiClient()
        answers = findViewById<RecyclerView>(R.id.rv_answers)!!

        val returnPage = intent.getStringExtra("page")
        val topicList: TopicResponseArray? = intent.getParcelableExtra("topicList")
        val position: String? = intent.getStringExtra("position")
        val topic = topicList?.topic?.get(position!!.toInt())
        val answerList = topic?.respostas

        val btnBack = findViewById<Button>(R.id.btnBack)
        val btnSave = findViewById<Button>(R.id.btnSave)
        val layoutAnswer = findViewById<LinearLayout>(R.id.lyt_addAnswer)
        val btnAddAnswer = findViewById<Button>(R.id.btn_add_answer)


        answerAdapter = AnswerListAdapter(this, answerList, this)

        val layoutManager = LinearLayoutManager(this)

        answers.layoutManager = layoutManager

        answers.adapter = answerAdapter

        btnSave.setOnClickListener {
            val answerField = findViewById<EditText>(R.id.ipt_answer)
            val answer = answerField.text.toString()

            if (answer.isNotBlank()) {
                newAnswer = Answer(
                    idTopico = topic!!.idTopico,
                    resposta = answer
                )
                addAnswer(newAnswer)
            }
        }

        btnAddAnswer.setOnClickListener {
            layoutAnswer.visibility = android.view.View.VISIBLE
        }

        btnBack.setOnClickListener {
            if (returnPage!!.contains("myQuestions")) {
                page = Intent(applicationContext, MyQuestions::class.java)
                page.putExtra("btn_text", "Ver todos os tópicos")
            } else {
                page = Intent(applicationContext, AllQuestions::class.java)
                page.putExtra("btn_text", "Ver meus tópicos")
            }
            startActivity(page)
        }
    }

    fun addAnswer(newAnswer: Answer) {
        apiClient.getMainApiService(this.applicationContext).registerAnswer(newAnswer)
            .enqueue(object : Callback<Answer> {
                @SuppressLint("NotifyDataSetChanged")
                override fun onResponse(
                    call: Call<Answer>,
                    response: Response<Answer>
                ) {
                    if (response.isSuccessful) {
                        val answer = response.body()
                        Log.w("NEW ANSWER", "${newAnswer}")
                        Log.w("RESPONSE BODY NEW ANSWER", "${answer}")
                        answerAdapter.notifyDataSetChanged()

                    } else {
                        Log.e(
                            "ERRO AO CRIAR NOVA ANSWER",
                            "Call: ${call} Response: ${response} NEW ANSWER: ${newAnswer}"
                        )
                    }
                }

                override fun onFailure(call: Call<Answer>, t: Throwable) {
                    t.printStackTrace()
                    Log.e(
                        "ERRO NO SERVIDOR AO CADASTRAR NOVA ANSWER",
                        "Call: ${call}"
                    )
                }
            })
    }

    override fun onItemClick(position: Int) {
        Log.i("CLIQUE", "clicou na resposta")
    }
}