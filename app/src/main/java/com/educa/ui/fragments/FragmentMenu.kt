package com.educa.ui.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.annotation.Nullable
import androidx.fragment.app.Fragment
import com.educa.Content
import com.educa.MyQuestions
import com.educa.R

class FragmentMenu : Fragment() {


    private lateinit var btnOpenDrawer: ImageButton
    private lateinit var btnCloseDrawer: ImageButton
    private lateinit var drawerLayout: View
    private lateinit var backgroundOverlay: View

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    @Nullable
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val homePage = view.findViewById<TextView>(R.id.tv_home_page)
        val doubtForum = view.findViewById<TextView>(R.id.tv_doubt_forum)

        homePage.setOnClickListener {
            val content = Intent(activity, Content::class.java)
            startActivity(content)
        }

        doubtForum.setOnClickListener {
            val myQuestions = Intent(activity, MyQuestions::class.java)
            myQuestions.putExtra("btn_text", "Ver todos os tópicos")
            startActivity(myQuestions)
        }

        btnOpenDrawer = view.findViewById(R.id.btnOpenDrawer)
        btnCloseDrawer = view.findViewById(R.id.btnCloseDrawer)
        drawerLayout = view.findViewById(R.id.drawerLayout)
        backgroundOverlay = view.findViewById(R.id.backgroundOverlay)

        btnOpenDrawer.setOnClickListener {
            openMenu()
        }
        btnCloseDrawer.setOnClickListener {
            closeMenu()
        }
        backgroundOverlay.setOnClickListener {
            closeMenu()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_menu, container, false)
    }

    private fun openMenu() {
        // Mostrar a camada de fundo
        backgroundOverlay.visibility = View.VISIBLE

        if (drawerLayout.visibility == View.VISIBLE) {
            drawerLayout.visibility = View.GONE

        } else {
            drawerLayout.visibility = View.VISIBLE
        }
    }

    private fun closeMenu() {
        // Esconder a camada de fundo
        backgroundOverlay.visibility = View.GONE

        if (drawerLayout.visibility == View.VISIBLE) {
            drawerLayout.visibility = View.GONE

        } else {
            drawerLayout.visibility = View.VISIBLE
        }
    }
}

