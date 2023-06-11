package com.educa.ui.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import com.educa.R
import com.educa.api.model.Rating
import com.educa.api.service.ApiClient
import com.educa.api.service.SessionManager
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class Likert : Fragment() {
    private lateinit var sessionManager: SessionManager
    private lateinit var apiClient: ApiClient
    private lateinit var rating: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view: View = inflater.inflate(R.layout.fragment_likert, container, false)
        apiClient = ApiClient()
        sessionManager = SessionManager(view.context)

        val contentId: String? = activity?.intent?.getStringExtra("contentId")

        val dislike = view.findViewById<ImageView>(R.id.img_notlike)
        dislike.setOnClickListener {
            rating = "Não gostei"
            val newRating = Rating(
                idConteudo = contentId,
                avaliacao = rating
            )
            sendLikert(view, newRating)
        }
        val improve = view.findViewById<ImageView>(R.id.img_improve)
        improve.setOnClickListener {
            rating = "Poderia melhorar"
            val newRating = Rating(
                idConteudo = contentId,
                avaliacao = rating
            )
            sendLikert(view, newRating)
        }
        val doubt = view.findViewById<ImageView>(R.id.img_doubt)
        doubt.setOnClickListener {
            rating = "Não sei"
            val newRating = Rating(
                idConteudo = contentId,
                avaliacao = rating
            )
            sendLikert(view, newRating)
        }
        val liked = view.findViewById<ImageView>(R.id.img_liked)
        liked.setOnClickListener {
            rating = "Gostei"
            val newRating = Rating(
                idConteudo = contentId,
                avaliacao = rating
            )
            sendLikert(view, newRating)
        }
        val loved = view.findViewById<ImageView>(R.id.img_love)
        loved.setOnClickListener {
            rating = "Amei!"
            val newRating = Rating(
                idConteudo = contentId,
                avaliacao = rating
            )
            sendLikert(view, newRating)
        }


        return view
    }

    fun sendLikert(view: View, newRating: Rating) {
        Log.e("NEW RATING AVALICAO", newRating.toString())
        apiClient.getMainApiService(view.context).registerRating(newRating)
            .enqueue(object : Callback<Rating> {
                override fun onResponse(
                    call: Call<Rating>,
                    response: Response<Rating>
                ) {
                    if (response.isSuccessful) {
                        val responseNewRating = response.body()
                        Log.w("AVALIACAO responseNewRating", "${responseNewRating}")

                    } else {
                        Toast.makeText(
                            context,
                            "Erro ao fazer cadastro, confirme seus dados e tente novamente!",
                            Toast.LENGTH_SHORT
                        ).show()

                        Log.e(
                            "ERRO AO CRIAR NOVA AVALIACAO",
                        "Call: ${call} Response: ${response} "
                        )
                    }

                }

                override fun onFailure(call: Call<Rating>, t: Throwable) {
                    Toast.makeText(
                        context,
                        "Erro no servidor! Por favor, tente novamente mais tarde. ERRO: ${t.message}",
                        Toast.LENGTH_SHORT
                    ).show()
                    t.printStackTrace()

                }
            })
    }
}
