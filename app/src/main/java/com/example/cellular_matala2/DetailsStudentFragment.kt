package com.example.cellular_matala2

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
    private val args: DetailsStudentFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDetailsStudentBinding.inflate(inflater, container, false)

        // Set the name, id, and status to the corresponding views
        binding?.nameTextView?.text = args.studentName
        binding?.idTextView?.text = args.studentId
        binding?.statusCheckBox?.isChecked = args.studentStatus

        binding?.editButton?.setOnClickListener {
            val action = DetailsStudentFragmentDirections.actionDetailsStudentFragmentToEditStudentFragment(
                args.studentName ?: "",
                args.studentId ?: "",
                args.studentStatus
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