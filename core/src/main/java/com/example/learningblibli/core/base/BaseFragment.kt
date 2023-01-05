package com.example.learningblibli.core.base

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.navigation.fragment.findNavController
import com.example.learningblibli.core.data.repository.AuthRepository
import com.example.learningblibli.core.ui.utils.LoadingDialog
import javax.inject.Inject

abstract class BaseFragment : Fragment(),MenuProvider {
    private var loadingDialog: LoadingDialog? = null

    fun showLoadingDialog() {

        if (loadingDialog == null) {
            activity?.let {
                loadingDialog = LoadingDialog(it)
            }
        }
        loadingDialog?.let {
            loadingDialog?.show()
        }

    }
    fun hideLoadingDialog(){
        loadingDialog?.let {
            it.dismissDialog()
            loadingDialog = null
        }
    }

    fun showToast(message:String){
        Toast.makeText(context,message,Toast.LENGTH_SHORT).show()
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupMenuProvider()
    }

    private fun setupMenuProvider() {
        activity?.let {
            val menuHost: MenuHost = it
            menuHost.addMenuProvider(this,viewLifecycleOwner,Lifecycle.State.STARTED)
        }
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