package com.example.moviesapp.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.moviesapp.R
import com.example.moviesapp.data.model.Movie
import com.example.moviesapp.ui.detail.DetailFragment
import com.example.moviesapp.ui.movie.MovieListFragment
import com.example.moviesapp.util.Navigator

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Navigator.addFragment(supportFragmentManager,MovieListFragment.newInstance(),R.id.container,MovieListFragment.TAG,false)
    }


    fun openDetailFragment(movie: Movie) {
        Navigator.addFragment(supportFragmentManager,DetailFragment.newInstance(movie),R.id.container, DetailFragment.TAG)
    }
}