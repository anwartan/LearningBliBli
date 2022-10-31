package com.example.learningblibli.core.data.repository

import android.net.Uri
import android.os.Parcel
import com.google.android.gms.internal.firebase_auth.zzff
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.*
import org.mockito.Mockito

class FakeFirebaseUser:FirebaseUser() {
    override fun writeToParcel(p0: Parcel?, p1: Int) {

    }

    override fun getUid(): String {
        return "123"
    }

    override fun getProviderId(): String {
        return "123"
    }

    override fun getDisplayName(): String? {
        return "admin"
    }

    override fun getPhotoUrl(): Uri? {
        return null
    }

    override fun getEmail(): String? {
        return "admin@gmail.com"
    }

    override fun getPhoneNumber(): String? {
        return "085760621628"
    }

    override fun isEmailVerified(): Boolean {
        return false
    }

    override fun isAnonymous(): Boolean {
       return false
    }

    override fun zza(): MutableList<String>? {
        return null
    }

    override fun zza(p0: MutableList<out UserInfo>): FirebaseUser {
        return Mockito.mock(FirebaseUser::class.java)
    }

    override fun zza(p0: zzff) {

    }

    override fun getProviderData(): MutableList<out UserInfo> {
        return mutableListOf()
    }

    override fun zzb(): FirebaseUser {
        return Mockito.mock(FirebaseUser::class.java)
    }

    override fun zzb(p0: MutableList<zzy>?) {

    }

    override fun zzc(): FirebaseApp {
        return Mockito.mock(FirebaseApp::class.java)
    }

    override fun zzd(): String? {
        return null
    }

    override fun zze(): zzff {
        return Mockito.mock(zzff::class.java)
    }

    override fun zzf(): String {
        return "123"
    }

    override fun zzg(): String {
        return "123"
    }

    override fun getMetadata(): FirebaseUserMetadata? {
        return null
    }

    override fun zzh(): zzz {
        return Mockito.mock(zzz::class.java)
    }
}