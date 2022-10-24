package com.example.learningblibli.base

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.navigation.fragment.findNavController
import com.example.learningblibli.ui.utils.LoadingDialog


abstract class BaseFragment : Fragment(),MenuProvider {
    private var loadingDialog: LoadingDialog? = null

    fun showLoadingDialog() {

        if (loadingDialog == null) {
            loadingDialog = LoadingDialog(requireActivity())
        }
        loadingDialog?.let {
            loadingDialog?.show()
        }

    }
    fun hideLoadingDialog(){
        loadingDialog?.let {
            it.dismisDialog()
            loadingDialog = null
        }

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupMenuProvider()
    }

    private fun setupMenuProvider() {
        val menuHost: MenuHost = requireActivity()
        menuHost.addMenuProvider(this,viewLifecycleOwner,Lifecycle.State.RESUMED)
    }

    override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
    }

    override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
        if(menuItem.itemId== android.R.id.home){
            findNavController().popBackStack()
        }
        return true
    }
}