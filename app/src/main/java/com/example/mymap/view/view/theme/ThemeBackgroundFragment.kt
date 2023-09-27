package com.example.mymap.view.view.theme

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import com.example.mymap.R
import com.example.mymap.databinding.FragmentThemeBackgroundBinding
import com.example.mymap.view.models.ImageItem

class ThemeBackgroundFragment : Fragment() {
    private lateinit var binding: FragmentThemeBackgroundBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentThemeBackgroundBinding.inflate(inflater, container, false)
        val rootView = binding.root
        binding.rcvThemeBackground.layoutManager = GridLayoutManager(requireContext(), 2)
        val data = listOf(
            ImageItem(R.drawable.nen_one),
            ImageItem(R.drawable.nen_two),
            ImageItem(R.drawable.nen_three),
            ImageItem(R.drawable.nen_four),
            ImageItem(R.drawable.nen_five),
            ImageItem(R.drawable.nen_six),
            ImageItem(R.drawable.nen_seven),
            ImageItem(R.drawable.nen_eight),
            ImageItem(R.drawable.nen_nine),
            ImageItem(R.drawable.nen_ten)
            // Thêm các ImageItem khác vào đây
        )
        val adapter = ImageBackgroundAdapter(data)
        binding.rcvThemeBackground.adapter = adapter
        return rootView
    }

}