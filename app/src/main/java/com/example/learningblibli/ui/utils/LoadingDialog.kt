package com.example.learningblibli.ui.utils


import android.app.Activity
import android.app.AlertDialog
import com.example.learningblibli.R


class LoadingDialog(private val activity:Activity) {
    private var alertDialog: AlertDialog? = null
    private var isShow:Boolean? = null
    fun show(){
        val customDialog = AlertDialog.Builder(activity)
        val inflater = activity.layoutInflater
        customDialog.setView(inflater.inflate(R.layout.dialog_loading, null))
        customDialog.setCancelable(false)
        alertDialog = customDialog.create()
        alertDialog?.show()
    }
    fun isShowing():Boolean{
        return if(isShow==null){
            false
        }else{
            isShow as Boolean
        }
    }
    fun dismisDialog() {
        alertDialog?.dismiss()
    }
}