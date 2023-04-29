package com.educa

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.annotation.Nullable
import androidx.fragment.app.Fragment

class FragmentMenu : Fragment() {


    private lateinit var btnOpenDrawer: ImageButton
    private lateinit var btnCloseDrawer: ImageButton
    private lateinit var drawerLayout: View

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    @Nullable
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btnOpenDrawer = view.findViewById(R.id.btnOpenDrawer)
        btnCloseDrawer = view.findViewById(R.id.btnCloseDrawer)
        drawerLayout = view.findViewById(R.id.drawerLayout)

        btnOpenDrawer.setOnClickListener {
            if (drawerLayout.visibility == View.VISIBLE) {
                drawerLayout.visibility = View.GONE
            } else {
                drawerLayout.visibility = View.VISIBLE
            }
        }

        btnCloseDrawer.setOnClickListener {
            if (drawerLayout.visibility == View.VISIBLE) {
                drawerLayout.visibility = View.GONE
            } else {
                drawerLayout.visibility = View.VISIBLE
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_menu, container, false)


    }

}