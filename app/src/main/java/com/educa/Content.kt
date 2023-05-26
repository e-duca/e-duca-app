package com.educa

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.educa.api.model.Content
import com.educa.api.service.ApiClient
import com.educa.ui.recyclerview.adapter.ContentListAdapter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Content : AppCompatActivity() {
    lateinit var contents: RecyclerView
    private lateinit var apiClient: ApiClient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_content)

        apiClient = ApiClient()
        contents = findViewById<RecyclerView>(R.id.rv_cards)

        lateinit var contentAdapter: ContentListAdapter

        val contentList = mutableListOf<Content>()

        contentAdapter = ContentListAdapter(this, contentList)

        fun loadApiList() {
            apiClient.getMainApiService().getAllContent().enqueue(object : Callback<List<Content>> {
                override fun onResponse(
                    call: Call<List<Content>>,
                    response: Response<List<Content>>
                ) {
                    if (response.isSuccessful && !response.body()!!.isNullOrEmpty()) {
                        contentList.addAll(response.body()!!)
                        contentAdapter.notifyDataSetChanged()
                    }
                }

                override fun onFailure(call: Call<List<Content>>, t: Throwable) {
                    t.printStackTrace()
                    Toast.makeText(
                        this@Content, t.message,
                        Toast.LENGTH_SHORT
                    ).show()
                }

            })
        }

        loadApiList()
    }
}