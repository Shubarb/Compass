package com.example.mymap.view.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.mymap.R

class QCAdapterViewPager(private val title: List<String>,private val detail: List<String>, private val img: List<Int>): RecyclerView.Adapter<QCAdapterViewPager.Pager2ViewHolder>() {

    inner class Pager2ViewHolder(item: View): RecyclerView.ViewHolder(item){
        val img: ImageView = item.findViewById(R.id.imgQC)
        val title: TextView = item.findViewById(R.id.txtTitle)
        val detail: TextView = item.findViewById(R.id.txtDetail)

        init {
            img.setOnClickListener {
                val position = adapterPosition
                Toast.makeText(item.context,"Click ${position+1}",Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): QCAdapterViewPager.Pager2ViewHolder {
        return Pager2ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_indicator,parent,false))
    }

    override fun onBindViewHolder(holder: QCAdapterViewPager.Pager2ViewHolder, position: Int) {
        holder.img.setImageResource(img[position])
        holder.title.text = title[position]
        holder.detail.text = detail[position]
    }

    override fun getItemCount(): Int {
        return img.size
    }

}