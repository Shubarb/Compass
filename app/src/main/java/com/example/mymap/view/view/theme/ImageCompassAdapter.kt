package com.example.mymap.view.view.theme

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mymap.databinding.ItemThemeCompassBinding
import com.example.mymap.view.models.ImageItem


class ImageCompassAdapter(
    private val dataCompass: List<ImageItem>,
    private val dataNeedle: List<ImageItem>,
    private val context: Context,
    private val onClick: (ImageItem,ImageItem) -> Unit

    ) :
    RecyclerView.Adapter<ImageCompassAdapter.ViewHolder>() {

    inner class ViewHolder(private var binding: ItemThemeCompassBinding) : RecyclerView.ViewHolder(binding.root) {
        fun onBind(imgCompass:ImageItem,imgNeedle:ImageItem){
            binding.imgCompassTheme.setImageResource(imgCompass.imageResource)
            binding.imgNeedleTheme.setImageResource(imgNeedle.imageResource)
            binding.compassItem.setOnClickListener {
                onClick(imgCompass,imgNeedle)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ItemThemeCompassBinding.inflate(LayoutInflater.from(context),parent,false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.onBind(dataCompass[position],dataNeedle[position])
    }

    override fun getItemCount(): Int {
        return dataCompass.size
    }
}