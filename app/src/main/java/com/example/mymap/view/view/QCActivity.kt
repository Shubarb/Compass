package com.example.mymap.view.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.viewpager2.widget.ViewPager2
import com.example.mymap.R
import com.example.mymap.databinding.ActivityQcactivityBinding
import com.example.mymap.view.view.main.HomeActivity
import me.relex.circleindicator.CircleIndicator3

class QCActivity : AppCompatActivity() {

    private var imgList = mutableListOf<Int>()
    private var titleList = mutableListOf<String>()
    private var detailList = mutableListOf<String>()
    private lateinit var binding: ActivityQcactivityBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_qcactivity)

        postToList()
        binding.viewPager2.adapter = QCAdapterViewPager(titleList,detailList,imgList)
        binding.viewPager2.orientation = ViewPager2.ORIENTATION_HORIZONTAL

        val indicator = findViewById<CircleIndicator3>(R.id.indicator)
        indicator.setViewPager(binding.viewPager2)

        btnClickEvent()

    }

    private fun btnClickEvent() {
        binding.btnSkip.setOnClickListener {
            val intent = Intent(this,HomeActivity::class.java)
            startActivity(intent)
        }
        binding.btnContinue.setOnClickListener {
            val intent = Intent(this,HomeActivity::class.java)
            startActivity(intent)
        }
    }

    private fun addToList(title:String,detail:String,img: Int){
        imgList.add(img)
        titleList.add(title)
        detailList.add(detail)
    }

    private fun postToList(){
//        for(i in 1..3){
            addToList("Digital Compass","The simplest navigation tool pointing \n" +
                    "to your destination",R.drawable.anh_first)
            addToList("Augmented Reality GPS navigation"," Conveniently find your destination anytime\n" +
                    "with cell phone in hand",R.drawable.anh_second)
            addToList("You are lost and can't find way?","Position the direction with compass",R.drawable.anh_third)
//        }
    }
}