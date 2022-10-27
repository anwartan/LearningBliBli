package com.example.learningblibli.core.domain.repository

import com.example.learningblibli.core.data.source.remote.Resource
import com.google.firebase.auth.FirebaseUser

interface IUserRepository {
    suspend fun signIn(email:String, password:String): Resource<FirebaseUser?>
    suspend fun signUp(email:String, password:String): Resource<FirebaseUser?>
    fun logout()
    fun getCurrentUser():FirebaseUser?
}