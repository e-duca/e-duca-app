package com.educa

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class FragmentModal : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_modal, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val btnOpenPopUp: Button = view.findViewById(R.id.btn_showPopup)

        btnOpenPopUp.setOnClickListener {
            val showPopUp = FragmentPopUp()
            showPopUp.show((activity as AppCompatActivity).supportFragmentManager, "showPopUp")
        }


    }
}