package com.example.cellular_matala2

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class AddStudentActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_add_student)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val saveButton: Button = findViewById(R.id.save_button)
        val cancelButton: Button = findViewById(R.id.cancel_button)

        val nameEditText: EditText = findViewById(R.id.studentName)
        val idEditText: EditText = findViewById(R.id.studentId)

        val savedMessageTextView: TextView = findViewById(R.id.add_student_activity_save_message_text_view)

        // Retrieve data from Intent
        val studentName = intent.getStringExtra("student_name")
        val studentId = intent.getStringExtra("student_id")

        // Pre-fill EditText fields if data is passed
        studentName?.let { nameEditText.setText(it) }
        studentId?.let { idEditText.setText(it) }

        cancelButton.setOnClickListener {
            finish()
        }

        saveButton.setOnClickListener {
            savedMessageTextView.text = "Name: ${nameEditText.text} ID: ${idEditText.text} is saved!!!..."
        }
    }
}
