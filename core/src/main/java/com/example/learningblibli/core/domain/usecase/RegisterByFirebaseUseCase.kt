package com.example.learningblibli.core.domain.usecase

import com.example.learningblibli.core.data.repository.AuthRepository
import com.example.learningblibli.core.data.source.remote.Resource
import com.google.firebase.auth.FirebaseUser
import javax.inject.Inject

class RegisterByFirebaseUseCase @Inject constructor(private val userRepository: AuthRepository) {
    suspend operator fun invoke(email:String,password:String): Resource<FirebaseUser?> = userRepository.signUp(email,password)

}