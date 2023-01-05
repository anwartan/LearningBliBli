package com.example.learningblibli.core.domain.usecase.contract

import com.example.learningblibli.core.data.source.remote.Resource
import com.google.firebase.auth.FirebaseUser

interface LoginByFirebaseUseCase {
    suspend operator fun invoke(email:String, password:String): Resource<FirebaseUser?>
}