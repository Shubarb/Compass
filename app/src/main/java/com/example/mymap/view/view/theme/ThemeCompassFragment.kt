package com.example.mymap.view.view.theme

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import com.example.mymap.R
import com.example.mymap.databinding.FragmentThemeCompassBinding
import com.example.mymap.view.models.ImageItem

class ThemeCompassFragment : Fragment() {
    private lateinit var binding: FragmentThemeCompassBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentThemeCompassBinding.inflate(inflater, container, false)
        val rootView = binding.root
        binding.rcvThemeCompass.layoutManager = GridLayoutManager(requireContext(), 2)
        val dataCompass = listOf(
            ImageItem(R.drawable.compass_one),
            ImageItem(R.drawable.compass_two),
            ImageItem(R.drawable.compass_three),
            ImageItem(R.drawable.compass_four),
            ImageItem(R.drawable.compass_five),
            ImageItem(R.drawable.compass_six),
            ImageItem(R.drawable.compass_seven),
            ImageItem(R.drawable.compass_eight),
            ImageItem(R.drawable.compass_nine),
            ImageItem(R.drawable.compass_ten),
            ImageItem(R.drawable.compass_elev),
            ImageItem(R.drawable.compass_twel),
            ImageItem(R.drawable.compass_thirdt)
            // Thêm các ImageItem khác vào đây
        )

        val dataNeedle = listOf(
            ImageItem(R.drawable.needle_one),
            ImageItem(R.drawable.needle_two),
            ImageItem(R.drawable.needle_three),
            ImageItem(R.drawable.needle_four),
            ImageItem(R.drawable.needle_five),
            ImageItem(R.drawable.needle_six),
            ImageItem(R.drawable.needle_seven),
            ImageItem(R.drawable.needle_eight),
            ImageItem(R.drawable.needle_nine),
            ImageItem(R.drawable.needle_ten),
            ImageItem(R.drawable.needle_elev),
            ImageItem(R.drawable.needle_twelve),
            ImageItem(R.drawable.needle_thirdteen)
        )
//        val adapter = ImageCompassAdapter(dataCompass,dataNeedle)
//        binding.rcvThemeCompass.adapter = adapter
        return rootView

    }

}