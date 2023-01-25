package com.joshua.test.ui.detail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.joshua.test.databinding.VariationItemLayoutBinding
import com.joshua.test.domain.domainModel.Variation

class VariationAdapter: ListAdapter<Variation, VariationAdapter.VH>(VariationDiffCallback) {

    class VH(private val binding: VariationItemLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(model: Variation) {
            binding.variation = model
            binding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val inflater = LayoutInflater.from(parent.context)
        return VH(VariationItemLayoutBinding.inflate(inflater, parent, false))
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.bind(getItem(position))
    }
}

val VariationDiffCallback = object : DiffUtil.ItemCallback<Variation>() {
    override fun areItemsTheSame(oldItem: Variation, newItem: Variation) =
        oldItem.longForm == newItem.longForm

    override fun areContentsTheSame(oldItem: Variation, newItem: Variation) =
        oldItem == newItem
}