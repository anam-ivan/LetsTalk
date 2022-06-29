package com.ivan.letstalk.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.NonNull
import androidx.recyclerview.widget.RecyclerView
import com.ivan.letstalk.R
import com.ivan.letstalk.model.AlkSideEffectsModel

internal class AlkSideEffectsAdapter(private var alkSideEffectsModel: List<AlkSideEffectsModel>) :
    RecyclerView.Adapter<AlkSideEffectsAdapter.MyViewHolder>() {
    @NonNull
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.alk_side_effetcs_row, parent, false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val movie = alkSideEffectsModel[position]
        holder.title.text = movie.getTitle()
    }

    override fun getItemCount(): Int {
        return alkSideEffectsModel.size
    }

    internal inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var title: TextView = view.findViewById(R.id.title)
    }
}