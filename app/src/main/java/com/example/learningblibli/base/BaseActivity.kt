package com.example.learningblibli.base

import androidx.appcompat.app.AppCompatActivity
import com.example.learningblibli.ui.utils.LoadingDialog

abstract class BaseActivity:AppCompatActivity() {
    private var loadingDialog:LoadingDialog? = null

    fun showLoadingDialog() {

        if (loadingDialog == null) {
            loadingDialog = LoadingDialog(this)
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
}