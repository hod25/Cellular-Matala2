package com.example.cellular_matala2

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ProgressBar
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.cellular_matala2.adapter.StudentsRecyclerAdapter
import com.example.cellular_matala2.databinding.FragmentStudentsListBinding
import com.example.cellular_matala2.model.Model
import com.example.cellular_matala2.model.Student
import com.example.cellular_matala2.model.dao.StudentDao

class StudentsListFragment : Fragment() {

    private var students: List<Student>? = null
    private var adapter: StudentsRecyclerAdapter? = null

    private var binding: FragmentStudentsListBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentStudentsListBinding.inflate(inflater, container, false)

        binding?.recyclerView?.setHasFixedSize(true)

        val layoutManager = LinearLayoutManager(context)
        binding?.recyclerView?.layoutManager = layoutManager

        adapter = StudentsRecyclerAdapter(students)

        adapter?.listener = object : OnItemClickListener {
            override fun onItemClick(position: Int) {
                Log.d("TAG", "On click Activity listener on position $position")
            }

            override fun onItemClick(student: Student?) {
                student?.let { selectedStudent ->
                    getStudentById(selectedStudent) { fetchedStudent ->
                        fetchedStudent?.let {
                            val action = StudentsListFragmentDirections.actionStudentsListFragmentToDetailsStudentFragment(it.name,it.id,it.isChecked,it.phone,it.address)
                            binding?.root?.let { rootView ->
                                Navigation.findNavController(rootView).navigate(action)
                            }
                        } ?: run {
                            Log.e("TAG", "Student not found")
                        }
                    }
                }
            }

        }

        binding?.recyclerView?.adapter = adapter

        val action = StudentsListFragmentDirections.actionGlobalAddStudentFragment()
        binding?.addStudentButton?.setOnClickListener(Navigation.createNavigateOnClickListener(action))

        return binding?.root
    }

    override fun onResume() {
        super.onResume()
        getAllStudents()
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }

    private fun getAllStudents() {

        binding?.progressBar?.visibility = View.VISIBLE

        Model.shared.getAllStudents {
            this.students = it
            adapter?.set(it)
            adapter?.notifyDataSetChanged()

            binding?.progressBar?.visibility = View.GONE
        }
    }

    private fun getStudentById(student: Student, callback: (Student) -> Unit) {
        binding?.progressBar?.visibility = View.VISIBLE

        Model.shared.getStudentById(student.id) { result ->
            this.students = listOf(result)
            adapter?.set(this.students)
            adapter?.notifyDataSetChanged()
            binding?.progressBar?.visibility = View.GONE
            callback(result)
        }
    }


}