package com.example.moviesapp.ui.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.moviesapp.R
import com.example.moviesapp.databinding.MovieListFragmentBinding
import com.example.moviesapp.di.inject
import javax.inject.Inject

class ListFragment : Fragment() {

    private lateinit var binding: MovieListFragmentBinding
    @Inject
    lateinit var viewModel: MovieViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.movie_list_fragment, container, false)
        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        inject(this, requireActivity().viewModelStore, requireContext())
        viewModel.loadNowPlaying()
    }

    companion object {
        const val TAG = "ListFragment"
        fun newInstance() = ListFragment()
    }

}