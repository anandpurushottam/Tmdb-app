package com.example.moviesapp.ui.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.moviesapp.R
import com.example.moviesapp.data.model.Movie
import com.example.moviesapp.databinding.MovieDetailFragmentBinding
import com.example.moviesapp.dagger.inject
import com.example.moviesapp.ui.movie.MovieViewModel
import javax.inject.Inject

class DetailFragment : Fragment() {

    private lateinit var binding: MovieDetailFragmentBinding
    @Inject
    lateinit var viewModel: MovieViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.movie_detail_fragment, container, false)
        return binding.root
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        inject(this, requireActivity().viewModelStore, requireContext())
    }

    companion object {
        const val TAG = "DetailFragment"
        fun newInstance(movie: Movie) = DetailFragment()
    }

}