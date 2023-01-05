package com.example.learningblibli.core.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.learningblibli.core.data.sharedPreferences.AppSharedPreferences
import com.example.learningblibli.core.ui.utils.LoadingDialog
import dagger.android.AndroidInjection
import javax.inject.Inject

abstract class BaseActivity:AppCompatActivity() {
    @Inject
    protected lateinit var sharedPreferences:AppSharedPreferences
    private var loadingDialog: LoadingDialog? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)

    }
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
            it.dismissDialog()
            loadingDialog = null
        }

    }
}