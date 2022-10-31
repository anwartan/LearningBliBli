package com.example.learningblibli.core.domain.usecase

import com.example.learningblibli.core.data.repository.AuthRepository
import com.example.learningblibli.core.data.source.remote.Resource
import com.example.learningblibli.lib_model.model.User
import javax.inject.Inject

class LoginByFirebaseUseCase @Inject constructor(private val userRepository: AuthRepository) {
    suspend operator fun invoke(email:String,password:String): Resource<User?> = userRepository.signIn(email,password)
}