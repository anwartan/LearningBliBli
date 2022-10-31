package com.example.learningblibli.core.ui.utils


import android.app.Activity
import android.app.AlertDialog
import com.example.learningblibli.core.R


class LoadingDialog(private val activity:Activity) {
    private var alertDialog: AlertDialog? = null
    fun show(){
        if(!isShowing()){
            val customDialog = AlertDialog.Builder(activity)
            val inflater = activity.layoutInflater
            customDialog.setView(inflater.inflate(R.layout.dialog_loading, null))
            customDialog.setCancelable(false)
            alertDialog = customDialog.create()
            alertDialog?.show()
        }
    }
    private fun isShowing():Boolean{
        return if(alertDialog==null){
            false
        }else{
            alertDialog?.isShowing as Boolean
        }
    }
    fun dismissDialog() {
        alertDialog?.dismiss()
    }
}