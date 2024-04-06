package com.lm.listmvvm.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.lm.listmvvm.databinding.LayoutCardBinding
import com.lm.listmvvm.model.DataItem

class ListCardAdapter(private val context: Context, private val list: List<DataItem>) :
    RecyclerView.Adapter<ListCardAdapter.ListCardViewHolder>() {
    private lateinit var binding: LayoutCardBinding

    inner class ListCardViewHolder(binding: LayoutCardBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: DataItem) {
            binding.apply {
                img.load(item.avatar)
                txtFirstName.text = item.firstName
                txtLastName.text = item.lastName
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListCardViewHolder {
        val inflater = LayoutInflater.from(context)
        binding = LayoutCardBinding.inflate(inflater, parent, false)
        return ListCardViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ListCardViewHolder, position: Int) {
        val item = list[position]
        holder.bind(item)
    }
}