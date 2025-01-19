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
import com.example.cellular_matala2.databinding.FragmentDetailsStudentBinding

class DetailsStudentFragment : Fragment() {

    private var binding: FragmentDetailsStudentBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.d("FragmentLifecycle", "Fragment created")
        binding = FragmentDetailsStudentBinding.inflate(inflater, container, false) // אתחול ה-binding

        val args = DetailsStudentFragmentArgs.fromBundle(requireArguments())
        Log.d("ARGS",args.studentName+args.studentId+args.studentPhone+args.studentAddress)
        binding?.apply {
            studentName.text = args.studentName
            studentId.text = args.studentId
            statusCheckBox.isChecked = args.studentStatus
            studentPhone.text = args.studentPhone
            studentAddress.text = args.studentAddress
        }

        binding?.editButton?.setOnClickListener {
            val action = DetailsStudentFragmentDirections.actionDetailsStudentFragmentToEditStudentFragment(
                args.studentName ?: "",
                args.studentId ?: "",
                args.studentStatus,
                args.studentPhone ?: "",
                args.studentAddress ?: ""
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