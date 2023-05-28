package com.educa

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.educa.api.model.ContentResponseArray
import com.educa.api.model.ContentResponse
import com.educa.api.service.ApiClient
import com.educa.api.service.SessionManager
import com.educa.ui.recyclerview.adapter.ContentListAdapter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Content : AppCompatActivity() {
    lateinit var contents: RecyclerView
    private lateinit var apiClient: ApiClient
    private lateinit var sessionManager: SessionManager
    lateinit var contentAdapter: ContentListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_content)

        apiClient = ApiClient()
        sessionManager = SessionManager(this)
        contents = findViewById<RecyclerView>(R.id.rv_cards)!!

        loadContentList()
        Log.i(
            "CONTEÚDO: CHAMOU A FUNÇÃO",
            "loadContentList"
        )
    }

    fun loadContentList() {
        val contentList = mutableListOf<ContentResponse>()
        contentAdapter = ContentListAdapter(this, contentList)

        val layoutManager = LinearLayoutManager(this)

        contents.layoutManager = layoutManager

        contents.adapter = contentAdapter

        Log.e(
            "CONTEÚDO: ENTROU NA FUNÇÃO LoadContentList e puxou o token",
            "FETCHED TOKEN: ${sessionManager.fetchAuthToken()}"
        )

        apiClient.getMainApiService()
            .getAllContent(token = "Bearer ${sessionManager.fetchAuthToken()}")
            .enqueue(object : Callback<ContentResponseArray> {
                @SuppressLint("NotifyDataSetChanged")
                override fun onResponse(
                    call: Call<ContentResponseArray>,
                    response: Response<ContentResponseArray>
                ) {
                    if (response.isSuccessful) {
                        response.body()?.content?.let { contentList.addAll(it) }
                        contentAdapter.notifyDataSetChanged()

                        Log.i(
                            "CONTEÚDO: ENTROU NO ISSUCCESSFUL",
                            "Call: ${call} Response: ${response.body()}"
                        )
                    } else {
                        Log.i(
                            "CONTEÚDO: ENTROU NO IF DO ISSUCCESSFUL MAS CAIU NO ELSE",
                            "Call: ${call}, Response: ${response.code()} ${response.body()})"
                        )
                    }
                }

                override fun onFailure(call: Call<ContentResponseArray>, t: Throwable) {
                    t.printStackTrace()
                    Toast.makeText(
                        this@Content, t.message,
                        Toast.LENGTH_SHORT
                    ).show()

                    Log.e(
                        "ERRO AO PUXAR CONTEUDO",
                        "Call: ${call} ${t.message} ${t.printStackTrace()}"
                    )
                }
            })
    }
}
