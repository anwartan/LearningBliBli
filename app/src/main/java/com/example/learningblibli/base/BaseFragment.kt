package com.example.learningblibli.base

import android.content.Context
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import com.example.learningblibli.ui.utils.LoadingDialog


abstract class BaseFragment : Fragment() {
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

}