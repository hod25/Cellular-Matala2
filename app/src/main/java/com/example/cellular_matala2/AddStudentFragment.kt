package com.example.cellular_matala2

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.navigation.Navigation
import com.example.cellular_matala2.BlueFragment.Companion.TITLE
import com.example.cellular_matala2.databinding.FragmentAddStudentBinding
import com.example.cellular_matala2.model.Model
import com.example.cellular_matala2.model.Student

class AddStudentFragment : Fragment() {

    private var binding: FragmentAddStudentBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAddStudentBinding.inflate(inflater, container, false)
        binding?.cancelButton?.setOnClickListener(::onCancelClicked)
        binding?.saveButton?.setOnClickListener(::onSaveClicked)
        return binding?.root
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        menu.clear()
        super.onCreateOptionsMenu(menu, inflater)
    }

    private fun onSaveClicked(view: View) {

        val student = Student(
            name = binding?.nameEditText?.text?.toString() ?: "",
            id = binding?.idEditText?.text?.toString() ?: "",
            avatarUrl = "",
            isChecked = false
        )

        binding?.progressBar?.visibility = View.VISIBLE

        Model.shared.add(student) {
            binding?.progressBar?.visibility = View.GONE
            Navigation.findNavController(view).popBackStack()
        }
    }

    private fun onCancelClicked(view: View) {
        Navigation.findNavController(view).popBackStack()
    }
    fun newInstance(title: String): AddStudentFragment {
        return AddStudentFragment().apply {
            arguments = Bundle().apply {
                putString(TITLE, title)
            }
        }
    }
}