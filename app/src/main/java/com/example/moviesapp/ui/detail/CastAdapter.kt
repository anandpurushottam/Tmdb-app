package com.example.moviesapp.ui.detail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.moviesapp.data.model.Cast
import com.example.moviesapp.databinding.CastItemBinding

class CastAdapter(private val list: List<Cast>) : RecyclerView.Adapter<CastAdapter.ViewHolder>() {

    inner class ViewHolder(val binding: CastItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(CastItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.model= list[position]
        holder.binding.executePendingBindings()
    }

    override fun getItemCount(): Int {
        return list.size
    }
}
