package com.ivan.letstalk.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.ivan.letstalk.R
import com.ivan.letstalk.model.FaqModel

class FaqAdapter(var faqList: List<FaqModel>) :
    RecyclerView.Adapter<FaqAdapter.MovieVH>() {
    private lateinit var context : Context
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieVH {
        context  = parent.context
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.faq_row, parent, false)
        return MovieVH(view)
    }

    override fun onBindViewHolder(holder: MovieVH, position: Int) {
        val faqs = faqList[position]
        holder.faqTitle.text = faqs.title
        holder.faqDesc.text = faqs.desc
        val isExpanded: Boolean = faqList[position].isExpanded
        holder.expandableLayout.visibility = if (isExpanded) View.VISIBLE else View.GONE
        if (isExpanded){
            holder.expandView.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_minus))
        } else {
            holder.expandView.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_plus))
        }
    }

    override fun getItemCount(): Int {
        return faqList.size
    }

    inner class MovieVH(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var expandableLayout: ConstraintLayout
        var faqTitle: TextView
        var faqDesc: TextView
        var expandView: ImageView

        init {
            faqTitle = itemView.findViewById(R.id.faq_title)
            faqDesc = itemView.findViewById(R.id.faq_desc)
            expandableLayout = itemView.findViewById(R.id.expandableLayout)
            expandView = itemView.findViewById(R.id.iv_expand)
            expandView.setOnClickListener {
                val movie = faqList[absoluteAdapterPosition]
                // movie.isExpanded(!movie.isExpanded)
                movie.isExpanded = (!movie.isExpanded)
                notifyItemChanged(absoluteAdapterPosition)
            }
        }
    }

    companion object {
        private const val TAG = "FaqAdapter"
    }
}