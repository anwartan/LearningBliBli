package com.example.learningblibli.domain.usecase

import com.example.learningblibli.data.repository.AuthRepository
import com.example.learningblibli.data.source.remote.Resource
import com.google.firebase.auth.FirebaseUser
import javax.inject.Inject

class LogoutByFirebaseUseCase @Inject constructor(private val userRepository: AuthRepository) {
    operator fun invoke()= userRepository.logout()
}