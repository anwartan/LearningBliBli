package com.example.learningblibli
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.widget.SearchView

import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.learningblibli.base.BaseActivity
import com.example.learningblibli.databinding.ActivityMainBinding

class MainActivity : BaseActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.appBarMain.toolbar)
        setupNavController()
        setupAppbarConfiguration()
        setupBottomNavView()
        setupNavView()

        setupActionBarWithNavController(navController, appBarConfiguration)

    }
    private fun useDrawer():Boolean{
        return false
    }
    private fun setupAppbarConfiguration(){
        appBarConfiguration = if(useDrawer()){
            AppBarConfiguration(navController.graph,binding.drawerLayout)
        }else{
            AppBarConfiguration(navController.graph)
        }
    }
    private fun setupNavController() {
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment_content_main) as NavHostFragment
        navController = navHostFragment.navController

        val hideBottomList = arrayListOf(R.id.detailFragment)
        val hideToolbar = arrayListOf(R.id.detailFragment)
        navController.addOnDestinationChangedListener { _, destination, _ ->
            val id = destination.id
            if(hideToolbar.contains(id)){
                hideToolbar()
            }else{
                showToolbar()
            }
            if(hideBottomList.contains(id)){
                hideBottomView()
            }else{
                showBottomView()
            }
        }
    }
    private fun hideToolbar(){
        with(binding.appBarMain.toolbar){
            visibility=View.GONE
        }
    }
    private fun showToolbar(){
        with(binding.appBarMain.toolbar){
            visibility=View.VISIBLE
        }
    }

    private fun hideBottomView(){
        with(binding.appBarMain.contentMain.bottomNav){
            visibility=View.GONE
        }
    }
    private fun showBottomView(){
        with(binding.appBarMain.contentMain.bottomNav){
            visibility=View.VISIBLE
        }
    }

    private fun setupNavView() {
        with(binding.navView){
            setupWithNavController(navController)
        }
    }

    private fun setupBottomNavView() {
        with( binding.appBarMain.contentMain.bottomNav){
            setupWithNavController(navController)
        }
    }


    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }



}