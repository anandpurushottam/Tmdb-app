package com.example.moviesapp.ui.movie

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.moviesapp.R
import com.example.moviesapp.dagger.inject
import com.example.moviesapp.data.model.Movie
import com.example.moviesapp.databinding.MovieListFragmentBinding
import com.example.moviesapp.ui.MainActivity
import com.example.moviesapp.ui.movie.adapter.MovieAdapter
import com.example.moviesapp.util.PaginationScrollListener
import javax.inject.Inject

class MovieListFragment : Fragment() {

    private lateinit var binding: MovieListFragmentBinding
    private var movieAdapter: MovieAdapter = MovieAdapter(ArrayList()) { onItemClicked(it) }

    @Inject
    lateinit var vm: MovieViewModel
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.movie_list_fragment, container, false)
        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        inject(this, requireActivity().viewModelStore, requireContext())
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.recyclerview.apply {
            adapter = movieAdapter
            addOnScrollListener(provideScrollListener(layoutManager))
        }
        observeLiveData()
    }

    private fun provideScrollListener(layoutManager: RecyclerView.LayoutManager?) =
        object : PaginationScrollListener(layoutManager as GridLayoutManager) {
            override fun loadMoreItems() {
                movieAdapter.addLoadingFooter()
                vm.loadMovie()
            }
            override val totalPageCount: Int
                get() = vm.totalPage
            override val isLastPage: Boolean
                get() = vm.currentPage == vm.totalPage
            override val isLoading: Boolean
                get() = vm.loading
        }

    private fun observeLiveData() {
        vm.movies.observe(viewLifecycleOwner, this::updateUi)
    }

    private fun updateUi(movies: List<Movie>) {
        movieAdapter.addAll(movies as ArrayList<Movie>)
    }

    private fun onItemClicked(movie: Movie) {
        getParentActivity()?.openDetailFragment(movie)
    }

    private fun getParentActivity() = activity as? MainActivity

    companion object {
        const val TAG = "ListFragment"
        fun newInstance() = MovieListFragment()
    }

}