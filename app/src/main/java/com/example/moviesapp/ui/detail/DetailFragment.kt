package com.example.moviesapp.ui.detail

import android.os.Bundle
import android.transition.Explode
import android.transition.Slide
import android.view.Gravity
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.example.moviesapp.R
import com.example.moviesapp.data.model.Movie
import com.example.moviesapp.databinding.MovieDetailFragmentBinding
import com.example.moviesapp.dagger.inject
import com.example.moviesapp.data.model.Cast
import com.example.moviesapp.ui.MainActivity
import com.example.moviesapp.ui.movie.MovieViewModel
import com.google.android.material.transition.platform.MaterialElevationScale
import com.google.android.material.transition.platform.MaterialFadeThrough
import com.google.android.material.transition.platform.MaterialSharedAxis
import javax.inject.Inject

class DetailFragment : Fragment() {

    private lateinit var binding: MovieDetailFragmentBinding

    @Inject
    lateinit var viewModel: MovieViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding =
            DataBindingUtil.inflate(inflater, R.layout.movie_detail_fragment, container, false)
        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        inject(this, requireActivity().viewModelStore, requireContext())
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val movie = arguments?.getParcelable<Movie>(KEY)
        binding.model = movie
        movie?.id?.let { viewModel.loadMovieCast(it) }

        binding.ivBack.setOnClickListener {
            getParentActivity()?.onBackPressed()
        }
        observeLiveData()
    }

    private fun observeLiveData() {
        viewModel.cast.observe(viewLifecycleOwner, { result ->
            updateUi(result.cast)
        })

    }

    private fun updateUi(it: List<Cast>) {
        val castAdapter = CastAdapter(it)
        binding.showCast=it.isNotEmpty()
        binding.recyclerview.apply {
            adapter = castAdapter
        }
    }

    private fun getParentActivity() = activity as? MainActivity


    companion object {
        const val TAG = "MovieFragment"
        private const val KEY = "ARTICLE_KEY"
        fun newInstance(movie: Movie): DetailFragment {
            val detailFragment = DetailFragment()
            detailFragment.apply {
                // enterTransition =MaterialSharedAxis(MaterialSharedAxis.Y, true)
                //exitTransition = MaterialSharedAxis(MaterialSharedAxis.Y, false)

                val args = Bundle()
                args.putParcelable(KEY, movie)
                arguments = args
            }

            return detailFragment
        }
    }

}