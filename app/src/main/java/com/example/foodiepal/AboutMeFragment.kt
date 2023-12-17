package com.example.foodiepal

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.material.floatingactionbutton.FloatingActionButton

class AboutMeFragment : Fragment() {

    private lateinit var aboutMeText: TextView
    private lateinit var fabAddDetail: FloatingActionButton

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_about_me, container, false)

        aboutMeText = view.findViewById(R.id.about_me_text)
        fabAddDetail = view.findViewById(R.id.fab_add_detail)

        // Set initial about me text (replace with your actual content)
        aboutMeText.text = "About me section"

        // Define click listener for the FloatingActionButton
        fabAddDetail.setOnClickListener {
            // Open a dialog or activity for adding a new detail
            // You can implement the logic for adding the detail
            // to the aboutMeText here
            val intent = Intent(context, AddDetailActivity::class.java)
            startActivityForResult(intent, REQUEST_CODE_ADD_DETAIL)
        }

        return view
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == REQUEST_CODE_ADD_DETAIL) {
            // Extract the added detail from the intent and update the aboutMeText
            val newDetail = data?.getStringExtra(EXTRA_NEW_DETAIL) ?: ""
            aboutMeText.append("\n$newDetail")
        }
    }

    companion object {
        const val REQUEST_CODE_ADD_DETAIL = 1
        const val EXTRA_NEW_DETAIL = "new_detail"
    }
}

class AddDetailActivity : AppCompatActivity() {

    private lateinit var newDetailEditText: EditText

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_detail)

        newDetailEditText = findViewById(R.id.new_detail_edit_text)

        // Set up save button click listener
        val saveButton = findViewById<Button>(R.id.save_button)
        saveButton.setOnClickListener {
            val newDetail = newDetailEditText.text.toString().trim()

            // Check if detail is empty
            if (newDetail.isEmpty()) {
                Toast.makeText(this, "Please enter a detail", Toast.LENGTH_SHORT).show()
            } else {
                // Send back the new detail to the AboutMeFragment
                val intent = Intent()
                intent.putExtra(AboutMeFragment.EXTRA_NEW_DETAIL, newDetail)
                setResult(RESULT_OK, intent)
                finish()
            }
        }

        // Set up cancel button click listener (optional)
        val cancelButton = findViewById<Button>(R.id.cancel_button)
        cancelButton.setOnClickListener {
            finish()
        }
    }
}


