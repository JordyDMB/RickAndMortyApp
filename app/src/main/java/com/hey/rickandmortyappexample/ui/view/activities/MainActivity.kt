package com.hey.rickandmortyappexample.ui.view.activities

import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.collection.arrayMapOf
import androidx.core.view.WindowCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.hey.rickandmortyappexample.R
import com.hey.rickandmortyappexample.databinding.ActivityMainBinding
import com.hey.rickandmortyappexample.ui.view.fragments.CharactersFragment
import com.hey.rickandmortyappexample.ui.view.fragments.EpisodesFragment
import com.hey.rickandmortyappexample.ui.view.fragments.LocationsFragment
import java.util.*

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        WindowCompat.setDecorFitsSystemWindows(window, true)
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        binding.viewPager2.adapter =  MainCollectionFragments(this)

        TabLayoutMediator(binding.tabLayout, binding.viewPager2) { tab, position ->
            when (position){
                0-> tab.text = "Characters"
                1-> tab.text = "Locations"
                2-> tab.text = "Episodes"
            }
        }.attach()

        binding.tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                when (tab?.text) {
                    "Characters" -> binding.viewPager2.currentItem = 0
                    "Locations" -> binding.viewPager2.currentItem = 1
                    "Episodes" -> binding.viewPager2.currentItem = 2
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
            }
        })

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    class MainCollectionFragments(fragmentActivity: FragmentActivity) : FragmentStateAdapter(fragmentActivity) {

        override fun getItemCount(): Int = 3

        override fun createFragment(position: Int): Fragment {
            return when (position) {
                0 -> CharactersFragment()
                1 -> LocationsFragment()
                2 -> EpisodesFragment()
                else -> Fragment()
            }

        }
    }


}

