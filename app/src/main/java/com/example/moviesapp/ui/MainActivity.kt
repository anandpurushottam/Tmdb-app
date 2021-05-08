package com.example.moviesapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.FragmentManager
import com.example.moviesapp.R
import com.example.moviesapp.databinding.MovieListFragmentBinding
import com.example.moviesapp.ui.list.ListFragment
import com.example.moviesapp.util.Navigator

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Navigator.addFragment(supportFragmentManager,ListFragment.newInstance(),R.id.container,ListFragment.TAG,false)
    }
}