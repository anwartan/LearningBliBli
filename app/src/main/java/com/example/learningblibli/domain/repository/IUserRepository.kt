package com.example.learningblibli.domain.repository

import androidx.lifecycle.LiveData
import com.example.learningblibli.data.source.remote.Resource
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.flow.Flow

interface IUserRepository {
    suspend fun signIn(email:String, password:String): Resource<FirebaseUser?>
    suspend fun signUp(email:String, password:String): Resource<FirebaseUser?>
    fun logout()
    fun getCurrentUser():LiveData<FirebaseUser?>
}