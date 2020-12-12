package com.app.koindi.ui.details

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.app.koindi.databinding.ActorItemLayoutBinding
import com.app.koindi.models.responses.ActorModel

class ActorsRecyclerAdapter : RecyclerView.Adapter<ActorsRecyclerAdapter.ViewHolder>() {

    private val items = ArrayList<ActorModel>()

    class ViewHolder(private val binding: ActorItemLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun onBind(item: ActorModel) {
            binding.item = item
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(
            ActorItemLayoutBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    fun updateList(data: List<ActorModel>) {
        items.addAll(data)
        notifyItemRangeInserted(items.size - data.size,items.size)
    }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.onBind(items[position])
    }
}