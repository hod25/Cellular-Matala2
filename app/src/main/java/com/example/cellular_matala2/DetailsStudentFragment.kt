package com.example.cellular_matala2

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import com.example.cellular_matala2.databinding.FragmentDetailsStudentBinding

class DetailsStudentFragment : Fragment() {

    private var binding: FragmentDetailsStudentBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDetailsStudentBinding.inflate(inflater, container, false)

        // Retrieve the saved data from shared preferences
        val sharedPreferences = activity?.getSharedPreferences("student_prefs", Context.MODE_PRIVATE)
        val studentName = sharedPreferences?.getString("studentName", "")
        val studentId = sharedPreferences?.getString("studentId", "")
        val studentStatus = sharedPreferences?.getBoolean("studentStatus", false) ?: false

        // Set the name, id, and status to the corresponding views
        binding?.nameTextView?.text = studentName
        binding?.idTextView?.text = studentId
        binding?.statusCheckBox?.isChecked = studentStatus

        binding?.editButton?.setOnClickListener {
            val action = DetailsStudentFragmentDirections.actionDetailsStudentFragmentToEditStudentFragment(
                studentName ?: "",
                studentId ?: "",
                studentStatus
            )
            Navigation.findNavController(it).navigate(action)
        }

        return binding?.root
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }
}