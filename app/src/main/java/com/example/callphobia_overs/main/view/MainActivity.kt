package com.example.callphobia_overs.main.view

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.callphobia_overs.R
import com.example.callphobia_overs.databinding.ActivityMainBinding
import com.example.callphobia_overs.main.core.Application
import com.example.callphobia_overs.main.view.checklist.CheckListResultFragment
import com.example.callphobia_overs.main.view.home.HomeFragment
import com.example.callphobia_overs.main.view.setting.SettingFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val LOG = "mainActivity"
    private lateinit var binding : ActivityMainBinding
    private var userName : String = ""
    private var userId : Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        userName = intent.getStringExtra("name").toString()
        userId = intent.getIntExtra("id", 0)

        setBottomNavigation()

        if(savedInstanceState == null){
            binding.mainBottomNav.selectedItemId = R.id.fragment_home
        }

        binding.mainBottomNav.itemIconTintList = null
    }

    private fun setBottomNavigation(){
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.fragContainer) as NavHostFragment
        val navController = navHostFragment.navController
        binding.mainBottomNav.setupWithNavController(navController)

        binding.mainBottomNav.setOnItemSelectedListener {
            when(it.itemId){
                R.id.fragment_checkList -> {
                    //val checkListSharedPreferences = getSharedPreferences("checkList", MODE_PRIVATE)
                    //val isCheck = checkListSharedPreferences.getBoolean("isCheck", false)

                    val isCheck = Application.preferManager.getIsCheck("checkList")
                    Log.d(LOG,isCheck.toString())

                    if(isCheck){
                        navController.navigate(R.id.checkListResultFragment)
                    } else {
                        navController.navigate(R.id.checkListNoDataFragment)
                    }

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

    fun showBottomNavigationView(){
        binding.mainBottomNav.visibility = View.VISIBLE
        binding.goHome.visibility = View.VISIBLE
    }

    fun hideBottomNavigationView(){
        binding.mainBottomNav.visibility = View.GONE
        binding.goHome.visibility = View.GONE
    }

    fun getUserName() : String {
        return userName
    }

    fun getUserId() : Int {
        return userId
    }

}