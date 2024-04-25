package com.example.callphobia_overs.main.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.example.callphobia_overs.R
import com.example.callphobia_overs.databinding.ActivityMainBinding
import com.example.callphobia_overs.main.view.checklist.CheckListResultFragment
import com.example.callphobia_overs.main.view.checklist.CheckListTestFragment
import com.example.callphobia_overs.main.view.home.HomeFragment
import com.example.callphobia_overs.main.view.setting.SettingFragment

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding
    private val manager = supportFragmentManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setBottomNavigation()

    }

    private fun setBottomNavigation(){
        binding.mainBottomNav.setOnItemSelectedListener {
            when(it.itemId){
                R.id.fragment_checkList -> {
                    CheckListResultFragment().changeFragment()
                }
                R.id.fragment_setting -> {
                    SettingFragment().changeFragment()
                }
            }
            return@setOnItemSelectedListener true
        }

        binding.goHome.setOnClickListener {
            HomeFragment().changeFragment()
        }

        binding.mainBottomNav.setOnItemReselectedListener {  }
    }

    private fun Fragment.changeFragment() {
        manager.beginTransaction().replace(R.id.showFrame, this).commitAllowingStateLoss()
    }

    public fun replaceFragment(fragment: Fragment){
        supportFragmentManager
    }
}