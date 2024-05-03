package com.example.callphobia_overs.main.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.callphobia_overs.R
import com.example.callphobia_overs.databinding.ActivityMainBinding
import com.example.callphobia_overs.main.view.checklist.CheckListResultFragment
import com.example.callphobia_overs.main.view.home.HomeFragment
import com.example.callphobia_overs.main.view.setting.SettingFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

       // binding.mainBottomNav.selectedItemId = R.id.fragment_home

        setBottomNavigation()
    }

    private fun setBottomNavigation(){
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.fragContainer) as NavHostFragment
        val navController = navHostFragment.navController
        binding.mainBottomNav.setupWithNavController(navController)

        binding.mainBottomNav.selectedItemId = R.id.fragment_home

        binding.mainBottomNav.setOnItemSelectedListener {
            when(it.itemId){
                R.id.fragment_checkList -> {
                    navController.navigate(R.id.checkListResultFragment)
                }
                R.id.fragment_setting -> {
                    //changeFragment(SettingFragment())
                    navController.navigate(R.id.settingFragment)
                }
                R.id.fragment_home -> {
                    navController.navigate(R.id.homeFragment)
                }
            }
            return@setOnItemSelectedListener true
        }

        binding.goHome.setOnClickListener {
            navController.navigate(R.id.homeFragment)
            binding.mainBottomNav.selectedItemId = R.id.fragment_home
        }

        binding.mainBottomNav.setOnItemReselectedListener {  }
    }

    /*
    private fun changeFragment(fragment: Fragment) {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragContainer, fragment)
            .commit()
    }
*/
}