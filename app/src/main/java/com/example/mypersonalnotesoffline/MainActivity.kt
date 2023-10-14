package com.example.mypersonalnotesoffline

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupActionBarWithNavController

class MainActivity : AppCompatActivity()  {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val navHost = supportFragmentManager.findFragmentById(R.id.fragments_container) as NavHostFragment
       val navController=navHost.navController
        setupActionBarWithNavController(navController)



    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.fragments_container)


        return super.onSupportNavigateUp() || navController.navigateUp()
    }

}