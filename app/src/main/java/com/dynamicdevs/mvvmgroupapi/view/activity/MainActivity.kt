package com.dynamicdevs.mvvmgroupapi.view.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.dynamicdevs.mvvmgroupapi.R
import com.dynamicdevs.mvvmgroupapi.databinding.ActivityMainBinding
import com.dynamicdevs.mvvmgroupapi.databinding.ActivitySearchidBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //next page
        binding.searchIdbutton.setOnClickListener {

        }
    }
}