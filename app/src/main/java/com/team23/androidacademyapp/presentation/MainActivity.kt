package com.team23.androidacademyapp.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.annotation.NonNull
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.NavigationUI.onNavDestinationSelected
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.team23.androidacademyapp.R
import com.team23.androidacademyapp.presentation.mentors.MentorsFragment
import com.team23.androidacademyapp.presentation.news.NewsFragment
import com.team23.androidacademyapp.presentation.title.TitleFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val navigation = findViewById<BottomNavigationView>(R.id.bottomNavigation)
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.myNavHostFragment)!!

        navigation.setupWithNavController(navHostFragment.findNavController())

    }
}
