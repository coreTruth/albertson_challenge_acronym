package com.joshua.test.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.joshua.test.databinding.LongFormItemLayoutBinding
import com.joshua.test.domain.domainModel.LongForm
import javax.inject.Inject

class LongFormAdapter @Inject constructor(): androidx.recyclerview.widget.ListAdapter<LongForm, LongFormAdapter.VH>(VariationDiffCallback) {
    private var itemClickListener: ((LongForm) -> Unit)? = null

    fun setOnItemClickListener(listener: (LongForm) -> Unit) {
        this.itemClickListener = listener
    }

    class VH(private val binding: LongFormItemLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(model: LongForm) {
            binding.longForm = model
            binding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val inflater = LayoutInflater.from(parent.context)
        return VH(LongFormItemLayoutBinding.inflate(inflater,parent,false))
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.bind(getItem(position))
        holder.itemView.setOnClickListener { itemClickListener?.invoke(getItem(position)) }
    }
}

val VariationDiffCallback = object : DiffUtil.ItemCallback<LongForm>() {
    override fun areItemsTheSame(oldItem: LongForm, newItem: LongForm) =
        oldItem.longForm == newItem.longForm

    override fun areContentsTheSame(oldItem: LongForm, newItem: LongForm) =
        oldItem == newItem
}