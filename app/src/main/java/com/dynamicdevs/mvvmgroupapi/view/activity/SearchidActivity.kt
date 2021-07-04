package com.dynamicdevs.mvvmgroupapi.view.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.dynamicdevs.mvvmgroupapi.R
import com.dynamicdevs.mvvmgroupapi.databinding.ActivitySearchidBinding

class SearchidActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySearchidBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySearchidBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //back to MainActivity
        binding.backButton.setOnClickListener {

        }
        //towards image by url
        binding.imageButton.setOnClickListener {

        }
    }
}