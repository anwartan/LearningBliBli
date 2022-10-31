package com.example.learningblibli.core.data.repository

import android.os.Parcel
import com.google.firebase.auth.AdditionalUserInfo
import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseUser

class FakeAuthResult:AuthResult {
    override fun describeContents(): Int {
        return 1
    }

    override fun writeToParcel(p0: Parcel?, p1: Int) {

    }

    override fun getUser(): FirebaseUser? {
        return FakeFirebaseUser()
    }

    override fun getAdditionalUserInfo(): AdditionalUserInfo? {
        return null
    }

    override fun getCredential(): AuthCredential? {
        return null
    }
}