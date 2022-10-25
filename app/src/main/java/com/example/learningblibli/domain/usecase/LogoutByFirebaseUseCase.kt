package com.example.learningblibli.domain.usecase

import com.example.learningblibli.data.repository.AuthRepository
import javax.inject.Inject

class LogoutByFirebaseUseCase @Inject constructor(private val userRepository: AuthRepository) {
    operator fun invoke()= userRepository.logout()
}