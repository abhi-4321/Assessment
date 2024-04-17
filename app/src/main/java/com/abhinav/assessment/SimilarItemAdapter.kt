package com.abhinav.assessment

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.res.ResourcesCompat
import androidx.core.net.toUri
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.abhinav.assessment.model.SimilarItemX
import com.bumptech.glide.Glide

class SimilarItemAdapter(private val context: Context) : ListAdapter<SimilarItemX,SimilarItemAdapter.ViewHolder>(DiffUtilCallback()) {

    class ViewHolder(item : View) : RecyclerView.ViewHolder(item){
        val image: ImageView = item.findViewById(R.id.image)
        val textView: TextView = item.findViewById(R.id.text)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.similar_item,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Glide.with(context).load(getItem(position).image.toUri()).into(holder.image)
        holder.textView.text = getItem(position).name
    }

}

class DiffUtilCallback : DiffUtil.ItemCallback<SimilarItemX>() {
    override fun areItemsTheSame(oldItem: SimilarItemX, newItem: SimilarItemX): Boolean {
        return oldItem._id == newItem._id
    }

    override fun areContentsTheSame(oldItem: SimilarItemX, newItem: SimilarItemX): Boolean {
        return oldItem == newItem
    }

}
