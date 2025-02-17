package com.example.cellular_matala2.model

import android.os.Looper
import androidx.core.os.HandlerCompat
import com.example.cellular_matala2.model.dao.AppLocalDb
import com.example.cellular_matala2.model.dao.AppLocalDbRepository
import java.util.concurrent.Executors

typealias StudentsCallback = (List<Student>) -> Unit
typealias SingleStudentCallBack = (Student) -> Unit
typealias EmptyCallback = () -> Unit

interface GetAllStudentsListener {
    fun onCompletion(students: List<Student>)
}

class Model private constructor() {

    private val database: AppLocalDbRepository = AppLocalDb.database
    private val executor = Executors.newSingleThreadExecutor()
    private val mainHandler = HandlerCompat.createAsync(Looper.getMainLooper())

    companion object {
        val shared = Model()
    }

    fun getAllStudents(callback: StudentsCallback) {
        executor.execute {
            val students = database.studentDao().getAllStudents()

            Thread.sleep(4000)

            mainHandler.post {
                callback(students)
            }
        }
    }

    fun getStudentById(studentId:String, callback: SingleStudentCallBack) {
        executor.execute {
            val student = database.studentDao().getStudentById(studentId)

            Thread.sleep(4000)

            mainHandler.post {
                callback(student)
            }
        }
    }

    fun updateStudent(student: Student, callback: EmptyCallback) {
        executor.execute {
            database.studentDao().updateStudent(student)

            Thread.sleep(4000)

            mainHandler.post {
                callback()
            }
        }
    }

    fun add(student: Student, callback: EmptyCallback) {
        executor.execute {
            database.studentDao().insertStudents(student)

            Thread.sleep(4000)

            mainHandler.post {
                callback()
            }
        }
    }

    fun delete(student: Student, callback: EmptyCallback) {
        executor.execute {
            database.studentDao().delete(student)

            Thread.sleep(4000)

            mainHandler.post {
                callback()
            }
        }
    }
}