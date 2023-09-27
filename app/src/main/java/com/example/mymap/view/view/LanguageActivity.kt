package com.example.mymap.view.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mymap.R
import com.example.mymap.databinding.ActivityLanguageBinding
import com.example.mymap.view.models.Language

class LanguageActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLanguageBinding
    private lateinit var langAdapter: LanguageAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_language)

        binding.rcvLanguage.layoutManager = LinearLayoutManager(this)

        // Tạo danh sách các ngôn ngữ (đối tượng Language)
        val languageList = listOf(
            Language("English", R.drawable.eng),
            Language("French", R.drawable.fran),
            Language("Germany", R.drawable.germ),
            Language("Italian", R.drawable.itali),
            Language("Korean", R.drawable.japan),
            Language("Norwegian", R.drawable.nor),
            Language("Polish", R.drawable.pol),
        )

        langAdapter = LanguageAdapter(languageList)
        binding.rcvLanguage.adapter = langAdapter

        binding.btnOk.setOnClickListener {
            val intent = Intent(this,QCActivity::class.java)
            startActivity(intent)
        }
    }
}