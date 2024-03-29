package com.example.theuniverseapp.apod.presentation.view

import android.app.Dialog
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.theuniverseapp.R
import com.example.theuniverseapp.apod.domain.model.ApodModel

class ApodPaggerAdapter(
    var apods: MutableList<ApodModel>,
    private val itemClickListener: (ApodModel) -> (Unit)
) : RecyclerView.Adapter<ApodPaggerAdapter.ApodViewHolder>() {

    fun reverseList() {
        apods = apods.asReversed()
        notifyDataSetChanged()
    }

    fun clearList() {
        apods.clear()
    }

    inner class ApodViewHolder(
        itemView: View,
        private val onItemClicked: (apod: ApodModel) -> Unit
    ) : RecyclerView.ViewHolder(itemView),View.OnClickListener {

        init {
            itemView.findViewById<ImageView>(R.id.iv_fragment_apod_pagger).setOnClickListener(this)
        }
        val title: TextView = itemView.findViewById(R.id.tvImageTitle_fragment_apod)
        val image: ImageView = itemView.findViewById(R.id.iv_fragment_apod_pagger)
        val desc: TextView = itemView.findViewById(R.id.tvImageDesc_fragment_apod)
        val date: TextView = itemView.findViewById(R.id.tv_date_item_apod)

        fun bind(apod: ApodModel) {
            title.text = apod.title
            image.load(apod.url)
            date.text = apod.date
            desc.text = apod.explanation

            }

        override fun onClick(p0: View?) {
            val position = adapterPosition
            val apod = apods.get(position)
            onItemClicked(apod)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ApodViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_pagger_apod, parent, false)
        return ApodViewHolder(view,itemClickListener)
    }

    override fun onBindViewHolder(holder: ApodViewHolder, position: Int) {
        val curApod = apods[position]
        holder.bind(curApod)

    }

    override fun getItemCount(): Int = apods.size
}