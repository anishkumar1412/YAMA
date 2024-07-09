package com.example.myapplication


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.viewpager2.widget.ViewPager2
import com.example.myapplication.R
import com.google.android.material.tabs.TabLayout

class tabLayout : AppCompatActivity() {

    private lateinit var tabLayout: TabLayout
    private lateinit var viewPager2: ViewPager2
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tab_layout)

       tabLayout=findViewById(R.id.tabLayout)
       viewPager2=findViewById(R.id.viewPager2)
    }
}