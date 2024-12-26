package com.example.cellular_matala2

import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment

class MainActivity : AppCompatActivity() {

    var blueFragment: BlueFragment? = null

    private var fragmentOne: StudentsListFragment? = null
    private var fragmentTwo: BlueFragment? = null
    private var fragmentThree: BlueFragment? = null
    private var fragmentFour: BlueFragment? = null
    private var buttonOne: Button? = null
    private var buttonTwo: Button? = null
    private var buttonThree: Button? = null
    private var buttonFour: Button? = null

    private var inDisplayFragment: Fragment? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // TODO: 1 - Set MainActivity Launcher ✅
        // TODO: 2 - Create fragment from xml ✅
        // TODO: 3 - Create a fragment programmatically ✅
        // TODO: 4 - Manage nav args ✅
        // TODO: 5 - Create a tab bar with multiple fragments 👨‍🎓
        // TODO: 6 - Refactor students list
        // TODO: 7 - GPS/Firebase

        fragmentOne = StudentsListFragment()
        fragmentTwo = BlueFragment.newInstance("2️⃣")
        fragmentThree = BlueFragment.newInstance("3️⃣")
        fragmentFour = BlueFragment.newInstance("4️⃣")

        buttonOne = findViewById(R.id.main_activity_button_one)
        buttonTwo = findViewById(R.id.main_activity_button_two)
        buttonThree = findViewById(R.id.main_activity_button_three)
        buttonFour = findViewById(R.id.main_activity_button_four)

        buttonOne?.setOnClickListener {
            display(fragmentOne)
        }

        buttonTwo?.setOnClickListener {
            display(fragmentTwo)
        }

        buttonThree?.setOnClickListener {
            display(fragmentThree)
        }

        buttonFour?.setOnClickListener {
            display(fragmentFour)
        }

        display(fragmentOne)
    }

    private fun display(fragment: Fragment?) {
        fragment?.let {
            supportFragmentManager.beginTransaction().apply {
                add(R.id.main_activity_frame_layout, it)

                inDisplayFragment?.let {
                    remove(it)
                }

                addToBackStack("TAG")
                commit()
            }

            inDisplayFragment = fragment
        }
    }

    private fun removeFragment() {
        blueFragment?.let { fragment ->
            supportFragmentManager.beginTransaction().apply {
                remove(fragment)
                commit()
            }
        }
        blueFragment = null
    }

    fun addFragment() {
        blueFragment = BlueFragment.newInstance("This is my text 👨‍🎓")
        blueFragment?.let {
            supportFragmentManager.beginTransaction().apply {
                add(R.id.main_activity_frame_layout, it)
                addToBackStack("TAG")
                commit()
            }
        }
    }
}