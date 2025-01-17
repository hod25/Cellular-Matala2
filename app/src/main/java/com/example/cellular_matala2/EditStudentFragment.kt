package com.example.cellular_matala2

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import com.example.cellular_matala2.databinding.FragmentEditStudentBinding
import com.example.cellular_matala2.model.Student

class EditStudentFragment : Fragment() {

    private var binding: FragmentEditStudentBinding? = null
    private val args: EditStudentFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentEditStudentBinding.inflate(inflater, container, false)

        // Set the name and id to the EditText fields
        binding?.nameEditText?.setText(args.studentName)
        binding?.idEditText?.setText(args.studentId)
        binding?.statusCheckBox?.isChecked = args.studentStatus
        binding?.phoneEditText?.setText(args.studentPhone)
        binding?.addressEditText?.setText(args.studentAddress)

        binding?.cancelButton?.setOnClickListener {
            Navigation.findNavController(it).navigateUp()
        }

        binding?.saveButton?.setOnClickListener {
            saveData()
            Navigation.findNavController(it).navigateUp()
        }

        return binding?.root
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }

    private fun saveData() {
        val sharedPreferences = activity?.getSharedPreferences("student_prefs", Context.MODE_PRIVATE)
        val editor = sharedPreferences?.edit()
        val student = Student(
            id = binding?.idEditText?.text.toString(),
            name = binding?.nameEditText?.text.toString(),
            avatarUrl = "", // Add the appropriate avatar URL if needed
            phone = binding?.phoneEditText?.text.toString(),
            address = binding?.addressEditText?.text.toString(),
            isChecked = binding?.statusCheckBox?.isChecked ?: false
        )
        editor?.putString("studentName", student.name)
        editor?.putString("studentId", student.id)
        editor?.putBoolean("studentStatus", student.isChecked)
        editor?.apply()
        Log.d("EditStudentFragment", "Data saved: ${student.name}, ${student.id}, ${student.isChecked}")
    }
}