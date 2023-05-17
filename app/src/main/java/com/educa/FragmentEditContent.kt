package com.educa

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity

class FragmentEditContent : Fragment() {



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_edit_content, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val editContent = view.findViewById<ImageButton>(R.id.edit_content)
        val deleteContent = view.findViewById<ImageButton>(R.id.delete_content)

        editContent.setOnClickListener {
            val showPopUp = FragmentModalUpdate()
            showPopUp.show((activity as AppCompatActivity).supportFragmentManager, "showPopUp")
        }
        deleteContent.setOnClickListener {
            val showPopUp = FragmentModalDelete()
            showPopUp.show((activity as AppCompatActivity).supportFragmentManager, "showPopUp")
        }

    }

}