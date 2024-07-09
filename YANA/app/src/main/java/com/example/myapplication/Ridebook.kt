
package com.example.myapplication

import ImageAdapter
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Button
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import kotlin.math.abs

class Ridebook : AppCompatActivity() {
    private lateinit var tabLayout: TabLayout
    private lateinit var viewPager2: ViewPager2
    private lateinit var imageSliderViewPager: ViewPager2
    private lateinit var handler: Handler
    private lateinit var imageList: ArrayList<Int>
    private lateinit var imageAdapter: ImageAdapter
    private lateinit var fragmentPageAdapter: FragmentpageAdapter
    private lateinit var numberPlate:TextView
    private lateinit var battery_percentage:TextView
    private lateinit var distance:TextView
    private lateinit var Range:TextView
    private lateinit var vehicle_price:TextView
    private lateinit var vehicle_name:TextView





    // Dummy number plate data
    private val numberPlates = listOf(
        "OD 06H 9333",
        "WB 23A 1234",
        "KA 05M 5678",
        "TN 10B 9876"
    )
    private val battery_percentages = listOf(
        "30%",
        "50%",
        "100%",
        "90%"
    )
    private val distances = listOf(
        "10Km",
        "2.5km",
        "1km",
        "800m"
    )
    private val Ranges = listOf(
        "50Km",
        "75km",
        "100km",
        "76km"
    )
    private val vehicle_prices = listOf(
        "₹ 120 /-",
        "₹ 230 /-",
        "₹ 90 /-",
        "₹ 99 /-"
    )
    private val vehicle_names = listOf(
        "YAMAHA X1 PRO",
        "YANA SPECIAL",
        "HERO HF DOWN",
        "OLA S1 PRO"
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.ridebook)

        numberPlate = findViewById<TextView>(R.id.vehicle_no)
        battery_percentage = findViewById<TextView>(R.id.battery_percentage)
        distance = findViewById<TextView>(R.id.distance)
        Range = findViewById<TextView>(R.id.Range)
        vehicle_price = findViewById<TextView>(R.id.vehicle_price)
        vehicle_name = findViewById<TextView>(R.id.vehicle_name)

        // Setup for TabLayout with ViewPager2 for fragments
        setupTabLayoutWithViewPager()

        // Setup for Image Slider
        setupImageSlider()
        val bookButton = findViewById<Button>(R.id.bookButton)

        // Set click listeners
        bookButton.setOnClickListener {
            when (tabLayout.selectedTabPosition) {
                0 -> {
                    // If "Hourly" tab is selected, start the hourly_page activity
                    val intent = Intent(this@Ridebook, hourly_page::class.java)
                    startActivity(intent)
                }
                1 -> {
                    // If "Weekly" tab is selected, start the weekly_page activity
                    val intent = Intent(this@Ridebook, weekly_page::class.java)
                    startActivity(intent)
                }
                2 -> {
                    // If "Monthly" tab is selected, start the monthly_page activity
                    val intent = Intent(this@Ridebook, monthly_page::class.java)
                    startActivity(intent)
                }
            }
        }

    }

    private fun setupTabLayoutWithViewPager() {
        tabLayout = findViewById(R.id.tabLayout)
        viewPager2 = findViewById(R.id.viewPager2)

        fragmentPageAdapter = FragmentpageAdapter(supportFragmentManager, lifecycle)
        viewPager2.adapter = fragmentPageAdapter

        tabLayout.addTab(tabLayout.newTab().setText("   Daily   "))
        tabLayout.addTab(tabLayout.newTab().setText("   Weekly   "))
        tabLayout.addTab(tabLayout.newTab().setText("   Monthly   "))

        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                viewPager2.currentItem = tab?.position ?: 0
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
                // Optionally do something here
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
                // Optionally do something here
            }
        })

    }

    private fun setupImageSlider() {
        imageSliderViewPager = findViewById(R.id.viewPager2) // Assuming you have a different ViewPager2 for images
        handler = Handler(Looper.myLooper()!!)
        imageList = ArrayList()

        // Dummy image data
        imageList.add(R.drawable.img1)
        imageList.add(R.drawable.img2)
        imageList.add(R.drawable.img5)
        imageList.add(R.drawable.img4)

        imageAdapter = ImageAdapter(imageList, imageSliderViewPager)
        imageSliderViewPager.adapter = imageAdapter
        imageSliderViewPager.offscreenPageLimit = 3
        imageSliderViewPager.clipToPadding = false
        imageSliderViewPager.clipChildren = false
        imageSliderViewPager.getChildAt(0).overScrollMode = RecyclerView.OVER_SCROLL_NEVER

        setUpTransformer()

        // Add onPageChangeCallback to update number plate text
        imageSliderViewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                // Update number plate text
                updateNumberPlateText(position)
             update_battery_percentage(position)
            update_distance(position)
            update_range(position)
            update_vehicle_price(position)
            update_vehicle_name(position)

            }
        })
    }

    private fun setUpTransformer() {
        val transformer = CompositePageTransformer().apply {
            addTransformer(MarginPageTransformer(40))
            addTransformer { page, position ->
                val r = 1 - abs(position)
                page.scaleY = 0.85f + r * 0.15f
            }
        }
        imageSliderViewPager.setPageTransformer(transformer)
    }

    private fun updateNumberPlateText(position: Int) {
        val numberPlateText = numberPlates.getOrNull(position) ?: "Unknown" // Get number plate or fallback to "Unknown"
        numberPlate.text = numberPlateText
    }
    private fun update_battery_percentage(position: Int) {
        val battery_percentageText = battery_percentages.getOrNull(position) ?: "Unknown" // Get number plate or fallback to "Unknown"
        battery_percentage.text = battery_percentageText
    }
    private fun update_distance(position: Int) {
        val distanceText = distances.getOrNull(position) ?: "Unknown" // Get number plate or fallback to "Unknown"
        distance.text = distanceText
    }
    private fun update_range(position: Int) {
        val rangeText = Ranges.getOrNull(position) ?: "Unknown" // Get number plate or fallback to "Unknown"
       Range.text = rangeText
    }
    private fun update_vehicle_price(position: Int) {
        val vehicle_priceText =vehicle_prices.getOrNull(position) ?: "Unknown" // Get number plate or fallback to "Unknown"
        vehicle_price.text =vehicle_priceText
    }
    private fun update_vehicle_name(position: Int) {
        val vehicle_nameText =vehicle_names.getOrNull(position) ?: "Unknown" // Get number plate or fallback to "Unknown"
       vehicle_name.text =vehicle_nameText
    }
}

