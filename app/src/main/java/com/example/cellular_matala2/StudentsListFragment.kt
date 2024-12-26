package com.example.cellular_matala2

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.cellular_matala2.adapter.StudentsRecyclerAdapter
import com.example.cellular_matala2.model.Student
import com.example.cellular_matala2.model.Model
import com.google.android.material.floatingactionbutton.FloatingActionButton

class StudentsListFragment : Fragment() {

    private var students: MutableList<Student>? = null
    private lateinit var adapter: StudentsRecyclerAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_students_list, container, false)

        students = Model.shared.students
        val recyclerView: RecyclerView = view.findViewById(R.id.students_list_activity_recycler_view)
        recyclerView.setHasFixedSize(true)

        val layoutManager = LinearLayoutManager(context)
        recyclerView.layoutManager = layoutManager

        adapter = StudentsRecyclerAdapter(students)
        recyclerView.adapter = adapter

        // Set up the FloatingActionButton click listener
        val fabAddStudent: FloatingActionButton = view.findViewById(R.id.fab_add_student)
        fabAddStudent.setOnClickListener {
            val intent = Intent(requireContext(), AddStudentActivity::class.java)
            startActivityForResult(intent, REQUEST_CODE_ADD_STUDENT)
        }

        return view
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_CODE_ADD_STUDENT && resultCode == Activity.RESULT_OK) {
            data?.getParcelableExtra<Student>("new_student")?.let { newStudent ->
                students?.add(newStudent)
                adapter.notifyItemInserted(students?.size?.minus(1) ?: 0)
            }
        }
    }

    companion object {
        private const val REQUEST_CODE_ADD_STUDENT = 1
    }
}