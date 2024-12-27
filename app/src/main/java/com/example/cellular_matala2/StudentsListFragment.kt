package com.example.cellular_matala2

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

        val adapter = StudentsRecyclerAdapter(students)

        adapter.listener = object : OnItemClickListener {
            override fun onItemClick(position: Int) {
                Log.d("TAG", "On click Activity listener on position $position")
            }

            override fun onItemClick(student: Student?) {
                Log.d("TAG", "On student clicked name: ${student?.name}")

                // Navigate to AddStudentActivity
                val intent = Intent(requireContext(), AddStudentActivity::class.java)
                intent.putExtra("student_name", student?.name)
                intent.putExtra("student_id", student?.id)
                startActivity(intent)
            }
        }

        recyclerView.adapter = adapter

        // Set up the FloatingActionButton click listener
        val fabAddStudent: FloatingActionButton = view.findViewById(R.id.fab_add_student)
        fabAddStudent.setOnClickListener {
            val intent = Intent(requireContext(), AddStudentActivity::class.java)
            startActivity(intent)
        }

        return view
    }
}