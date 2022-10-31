package com.example.learningblibli.ui.main

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatDelegate
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.NavOptions
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.learningblibli.R
import com.example.learningblibli.core.base.BaseActivity
import com.example.learningblibli.core.data.sharedPreferences.AppSharedPreferences
import com.example.learningblibli.core.utils.Constants
import com.example.learningblibli.databinding.ActivityMainBinding
import com.example.learningblibli.ui.login.AuthViewModel
import dagger.android.AndroidInjection
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject


class MainActivity : BaseActivity() {
    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController
    private val authViewModel  by viewModels<AuthViewModel> {
        factory
    }
    @Inject
    lateinit var factory: ViewModelProvider.Factory
    @Inject
    lateinit var sharedPreferences: AppSharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.appBarMain.toolbar)
        setupNavController()
        setupAppbarConfiguration()
        setupBottomNavView()
        getCurrentUser()
        setupActionBarWithNavController(navController, appBarConfiguration)
        getStateDarkMode()
        getStateResource()
    }
    private fun getStateDarkMode() {
        sharedPreferences.getBooleanAsLiveData(Constants.DARK_MODE).observe(this){
            if(it)  AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            else AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        }
    }
    private fun getStateResource(){
        lifecycleScope.launch {
            authViewModel.loading.collectLatest {
                if(it) showLoadingDialog()
                else hideLoadingDialog()
            }
        }
    }
    private fun getCurrentUser() {
        authViewModel.getCurrentUser()
        authViewModel.currentUser.observe(this){
            it?.let {
                val navOptions = NavOptions.Builder()
                    .setPopUpTo(navController.graph.id,true)
                    .build()
                navController.navigate(R.id.homeFragment, null, navOptions)
            }?: run {
                val navOptions = NavOptions.Builder()
                    .setPopUpTo(navController.graph.id, true)
                    .build()
                navController.navigate(R.id.loginFragment,null,navOptions)
            }
        }
    }

    private fun setupAppbarConfiguration(){
        val listAppBarConfig :Set<Int> = setOf(R.id.homeFragment)
        appBarConfiguration = AppBarConfiguration( listAppBarConfig)
    }
    private fun setupNavController() {
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment_content_main) as NavHostFragment
        navController = navHostFragment.navController

        val hideBottomList = arrayListOf(
            R.id.detailFragment,
            R.id.loginFragment,
            R.id.registerFragment,
            R.id.settingFragment
        )
        val hideToolbar = arrayListOf(
            R.id.detailFragment,
            R.id.loginFragment,
            R.id.registerFragment
        )
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