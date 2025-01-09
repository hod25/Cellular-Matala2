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
        editor?.putString("studentName", binding?.nameEditText?.text.toString())
        editor?.putString("studentId", binding?.idEditText?.text.toString())
        editor?.putBoolean("studentStatus", binding?.statusCheckBox?.isChecked ?: false)
        editor?.apply()
        Log.d("EditStudentFragment", "Data saved: ${binding?.nameEditText?.text}, ${binding?.idEditText?.text}, ${binding?.statusCheckBox?.isChecked}")
    }
}