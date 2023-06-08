package com.educa

import android.annotation.SuppressLint
import android.content.Intent
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
import com.educa.ui.adapters.ContentListAdapter
import com.educa.ui.recyclerview.RecyclerViewInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Content : AppCompatActivity(), RecyclerViewInterface {
    lateinit var contents: RecyclerView
    private lateinit var apiClient: ApiClient
    private lateinit var sessionManager: SessionManager
    lateinit var contentAdapter: ContentListAdapter
    lateinit var contentList: MutableList<ContentResponse>

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
        contentList = mutableListOf()
        contentAdapter = ContentListAdapter(this, contentList, this)

        val layoutManager = LinearLayoutManager(this)

        contents.layoutManager = layoutManager

        contents.adapter = contentAdapter

        Log.e(
            "CONTEÚDO: ENTROU NA FUNÇÃO LoadContentList e puxou o token",
            "FETCHED TOKEN: ${sessionManager.fetchAuthToken()}"
        )

        apiClient.getMainApiService(this)
            .getAllContent()
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

    override fun onItemClick(position: Int) {
        val accessContent =
            if (contentList[position].urlVideo !== null) {
                Intent(this.applicationContext, Video::class.java)
            } else {
                Intent(this.applicationContext, Reading::class.java)
            }

        accessContent.putExtra("title", contentList[position].titulo)
        accessContent.putExtra("text", contentList[position].texto)
        accessContent.putExtra("video", contentList[position].urlVideo)
        accessContent.putExtra("ability", contentList[position].habilidade.codigo)
        accessContent.putExtra("postedAt", contentList[position].dataCriacao)
        accessContent.putExtra("postedBy", contentList[position].usuario.nome)
        startActivity(accessContent)
    }

}
