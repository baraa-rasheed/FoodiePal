package com.example.foodiepal

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment

class ContactFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_contact, container, false)

        val phoneTextView = view.findViewById<TextView>(R.id.chef_phone_number)
        val emailTextView = view.findViewById<TextView>(R.id.chef_email_address)

        phoneTextView.text = "+1 641 543 4453"
        emailTextView.text ="chef@gmail.com"

        return view
    }
}
