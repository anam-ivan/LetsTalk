package com.ivan.letstalk.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.NonNull
import androidx.recyclerview.widget.RecyclerView
import com.ivan.letstalk.R
import com.ivan.letstalk.model.SideEffectsModel

internal class KnowYourSideEffectsAdapter(private var sideEffectsModel: List<SideEffectsModel>) :
    RecyclerView.Adapter<KnowYourSideEffectsAdapter.MyViewHolder>() {
    @NonNull
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.side_effects_row, parent, false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val movie = sideEffectsModel[position]
        holder.tvSideEffectName.text = movie.getAlkTitle()
        holder.tvSidEffectDesc.text = movie.getAlkDesc()
        holder.tvSideEffectSymptoms.text = movie.getAlkSymptoms()
    }

    override fun getItemCount(): Int {
        return sideEffectsModel.size
    }

    internal inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var tvSideEffectName: TextView = view.findViewById(R.id.tv_side_effect_name)
        var tvSidEffectDesc: TextView = view.findViewById(R.id.tv_side_effect_desc)
        var tvSideEffectSymptoms: TextView = view.findViewById(R.id.tv_side_effect_symptoms)
    }
}