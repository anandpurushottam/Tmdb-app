package com.example.moviesapp.util


import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.example.moviesapp.R

private const val POSTER_URL="https://image.tmdb.org/t/p/w500"

@BindingAdapter(value = ["setImageUrl"])
fun ImageView.bindImageUrl(id: String?) {
    if (!id.isNullOrBlank()) {
        Glide.with(context).clear(this)
        Glide.with(this)
            .load(POSTER_URL+id)
            .placeholder(R.color.black)
            .into(this)
    } else {
        this.setBackgroundResource(R.color.black);
    }
}

