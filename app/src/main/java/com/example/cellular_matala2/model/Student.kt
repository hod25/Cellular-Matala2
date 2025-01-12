package com.example.cellular_matala2.model

import android.location.Address
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Student(
    @PrimaryKey val id: String,
    val name: String,
    val avatarUrl: String,
    val phone : String,
    val address : String,
    var isChecked: Boolean
)
