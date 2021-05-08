package com.example.moviesapp.ui.movie.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.example.moviesapp.data.model.Movie
import com.example.moviesapp.databinding.LoadingItemBinding
import com.example.moviesapp.databinding.MovieItemBinding

class MovieAdapter(private val list: ArrayList<Movie>, val listener: (Movie) -> Unit) : RecyclerView.Adapter<MovieAdapter.ViewHolder>() {
    private val MOVIE_VIEW_TYPE = 0
    private val LOADER_VIEW_TYPE = 1
    private var isLoadingAdded = false

    inner class ViewHolder : RecyclerView.ViewHolder {
        val binding: ViewDataBinding

        constructor(binding: MovieItemBinding) : super(binding.root) {
            this.binding = binding
            binding.root.setOnClickListener { listener.invoke(list[adapterPosition]) }
        }

        constructor(binding: LoadingItemBinding) : super(binding.root) {
            this.binding = binding
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return when (viewType) {
            LOADER_VIEW_TYPE -> {
                ViewHolder(LoadingItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
            }
            else -> {
                ViewHolder(MovieItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
            }
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val binding = holder.binding
        if (binding is MovieItemBinding) {
            binding.model = list[position]
        }
        binding.executePendingBindings()
    }

    override fun getItemViewType(position: Int): Int {
        return if (position == list.size.minus(1) && isLoadingAdded) LOADER_VIEW_TYPE else MOVIE_VIEW_TYPE
    }

    fun addAll(dataItem: ArrayList<Movie>) {
        removeLoadingFooter()
        list.addAll(dataItem)
        notifyDataSetChanged()
    }

    private fun removeLoadingFooter() {
        if (isLoadingAdded) {
            isLoadingAdded = false
            val position = list.size.minus(1)
            list.removeAt(position)
            notifyItemRemoved(position)
        }
    }

    fun addLoadingFooter() {
        isLoadingAdded = true
        list.add(Movie())
        notifyItemInserted(list.size - 1);
    }

    override fun getItemCount(): Int {
        return list.size
    }
}
