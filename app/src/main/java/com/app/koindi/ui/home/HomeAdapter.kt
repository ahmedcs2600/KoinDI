package com.app.koindi.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.app.koindi.databinding.LayoutMovieItemBinding
import com.app.koindi.models.responses.MovieModel

class HomeAdapter(private val block: (MovieModel) -> Unit) :
    RecyclerView.Adapter<HomeAdapter.ViewHolder>() {

    private val items = ArrayList<MovieModel>()

    class ViewHolder(private val binding: LayoutMovieItemBinding,private val block: (MovieModel) -> Unit) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(item: MovieModel) {
            binding.item = item

            binding.imgMoviePoster.setOnClickListener { block(item) }
        }
    }

    fun updateList(newData: List<MovieModel>) {
        items.addAll(newData)
        notifyItemRangeInserted(items.size - newData.size, items.size)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutMovieItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ),
            block
        )
    }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.onBind(items[position])
    }
}