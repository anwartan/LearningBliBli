package com.example.learningblibli.core.domain.repository

import com.example.learningblibli.core.data.source.remote.Resource
import com.example.learningblibli.lib_model.model.User

interface IUserRepository {
    suspend fun signIn(email:String, password:String): Resource<User?>
    suspend fun signUp(email:String, password:String): Resource<User?>
    fun logout()
    fun getCurrentUser():User?
}