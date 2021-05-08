package com.example.moviesapp.util


import android.view.View
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

@BindingAdapter(value = ["visibleUntil"])
fun View.visibleUntil(flag: Boolean?) {
    this.visibility = if (flag == true) View.VISIBLE else View.GONE
}
