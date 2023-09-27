package com.example.mymap.view.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RadioButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.mymap.R
import com.example.mymap.view.models.Language

class LanguageAdapter(private val languageList: List<Language>) :
    RecyclerView.Adapter<LanguageAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_language, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val language = languageList[position]
        holder.languageName.text = language.name
        holder.languageImage.setImageResource(language.imageResId)
        holder.radioButton.isChecked = language.isSelected

        holder.radioButton.setOnClickListener {
            language.isSelected = true
            notifyDataSetChanged()
        }
    }

    override fun getItemCount(): Int {
        return languageList.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val languageName: TextView = itemView.findViewById(R.id.txtLanguage)
        val languageImage: ImageView = itemView.findViewById(R.id.imgLanguage)
        val radioButton: RadioButton = itemView.findViewById(R.id.radioLanguage)
    }
}