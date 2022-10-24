package com.example.learningblibli.domain.usecase

import com.example.learningblibli.data.repository.AuthRepository
import com.example.learningblibli.data.source.remote.Resource
import com.google.firebase.auth.FirebaseUser
import javax.inject.Inject

class LoginByFirebaseUseCase @Inject constructor(private val userRepository: AuthRepository) {
    suspend operator fun invoke(email:String,password:String): Resource<FirebaseUser?> = userRepository.signIn(email,password)
}