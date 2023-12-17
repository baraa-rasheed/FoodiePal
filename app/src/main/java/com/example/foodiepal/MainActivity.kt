package com.example.foodiepal

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.ViewPager
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class MainActivity : AppCompatActivity() {
    private lateinit var tabLayout: TabLayout
    private lateinit var viewPager: ViewPager2
    private lateinit var bottomNavigationView: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bottomNavigationView = findViewById(R.id.bottom_navigation)

        bottomNavigationView.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_recipes -> {
                    // Navigate to your home screen
                    viewPager.setCurrentItem(0, true)
                    true
                }
                R.id.nav_meal_planner -> {
                    // Navigate to your recipes screen

                    viewPager.setCurrentItem(1, true)
                    true
                }
                R.id.nav_blog -> {
                    // Navigate to your recipes screen

                    viewPager.setCurrentItem(2, true)
                    true
                }
                else -> false
            }
        }

        tabLayout = findViewById(R.id.tabLayout)
        viewPager = findViewById(R.id.viewPager)

        val myPageAdapter = ViewPagerAdapter(this)
        viewPager.adapter = myPageAdapter;

        TabLayoutMediator(this.tabLayout,
            viewPager){tab,position->
            when(position){
                0->{
                    tab.text="Receipies"
                    tab.setIcon(R.drawable.recipe_book)
                }
                1->{
                    tab.text="Meal Planner"
                    tab.setIcon(R.drawable.nutrition)
                }
                2->{
                    tab.text="Blog"
                    tab.setIcon(R.drawable.blog)
                }
                3->{
                    tab.text = "Contact"
                    tab.setIcon(R.drawable.contact_mail)
                }
                4->{
                    tab.text = "About Me"
                    tab.setIcon(R.drawable.info)
                }
            }
        }.attach()
    }
}

class ViewPagerAdapter(fragmentActivity:FragmentActivity) :
    FragmentStateAdapter(fragmentActivity) {
    override fun getItemCount(): Int {
        return 5;
    }

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> RecipesFragment()
            1 -> MealPlannerFragment()
            2 -> BlogFragment()
            3 -> ContactFragment()
            4 -> AboutMeFragment()
            else -> Fragment()
        }
    }
}