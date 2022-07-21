package com.ivan.letstalk.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.ivan.letstalk.R
import com.ivan.letstalk.model.CommonModel

class MyHealthCardAdapter(var healthDetails: List<CommonModel>) :
    RecyclerView.Adapter<MyHealthCardAdapter.MovieVH>() {
    private lateinit var context: Context
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieVH {
        context = parent.context
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.my_health_card_row, parent, false)
        return MovieVH(view)
    }

    override fun onBindViewHolder(holder: MovieVH, position: Int) {
        val healthCard = healthDetails[position]
        holder.tvName.text = healthCard.name
        holder.tvValueDetails.text = healthCard.valueDetails
        if (healthCard.valueDetails.isEmpty()) {
            holder.tvValueDetails.text = healthCard.value
        }
    }

    override fun getItemCount(): Int {
        return healthDetails.size
    }

    inner class MovieVH(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tvName: TextView
        var tvValueDetails: TextView

        init {
            tvName = itemView.findViewById(R.id.tv_name)
            tvValueDetails = itemView.findViewById(R.id.tv_value_details)
        }
    }

    companion object {
        private const val TAG = "MyHealthCardAdapter"
    }
}