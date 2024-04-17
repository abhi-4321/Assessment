package com.abhinav.assessment

import android.content.Context
import android.content.res.ColorStateList
import android.graphics.Color
import android.graphics.ColorSpace
import android.provider.CalendarContract.Colors
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.card.MaterialCardView

class FactsAdapter(private val colors : List<Int>) : ListAdapter<String, FactsAdapter.ViewHolder>(DiffUtilCallback()) {

    class ViewHolder(item: View) : RecyclerView.ViewHolder(item) {
        val fact: TextView = item.findViewById(R.id.fact)
        val root: MaterialCardView = item.findViewById(R.id.root)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.facts_layout,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.fact.text = getItem(position)
        holder.root.setCardBackgroundColor(colors[position])
    }

    class DiffUtilCallback() : DiffUtil.ItemCallback<String>() {
        override fun areItemsTheSame(oldItem: String, newItem: String): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: String, newItem: String): Boolean {
            return oldItem.contentEquals(newItem)
        }

    }
}