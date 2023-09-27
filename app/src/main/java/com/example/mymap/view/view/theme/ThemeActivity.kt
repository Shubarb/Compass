package com.example.mymap.view.view.theme

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.viewpager2.widget.ViewPager2
import com.example.mymap.R
import com.example.mymap.databinding.ActivityThemeBinding
import com.google.android.material.tabs.TabLayout

class ThemeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityThemeBinding
    private lateinit var adapter: FragmentPageAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_theme)

        adapter = FragmentPageAdapter(supportFragmentManager, lifecycle)
        binding.tabLayoutThemeActivity.addTab(binding.tabLayoutThemeActivity.newTab().setText("ComPass"))
        binding.tabLayoutThemeActivity.addTab(binding.tabLayoutThemeActivity.newTab().setText("BackGround"))
        binding.viewPager2ThemeActivity.adapter = adapter
        binding.tabLayoutThemeActivity.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener{
            override fun onTabSelected(tab: TabLayout.Tab?) {
                if(tab != null){
                    binding.viewPager2ThemeActivity.currentItem = tab.position
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {

            }

            override fun onTabReselected(tab: TabLayout.Tab?) {

            }

        })

        binding.viewPager2ThemeActivity.registerOnPageChangeCallback(object :ViewPager2.OnPageChangeCallback(){
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                binding.tabLayoutThemeActivity.selectTab(binding.tabLayoutThemeActivity.getTabAt(position))
            }
        })

    }
}