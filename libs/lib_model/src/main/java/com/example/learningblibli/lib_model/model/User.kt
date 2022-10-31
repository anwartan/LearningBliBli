package com.example.learningblibli.lib_model.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
data class User (
    val uid:String,
    val email:String?=null,
    val displayName:String?=null
):Parcelable