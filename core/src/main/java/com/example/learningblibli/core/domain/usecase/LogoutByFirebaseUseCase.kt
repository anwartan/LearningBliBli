package com.example.learningblibli.core.domain.usecase

import com.example.learningblibli.core.data.repository.AuthRepository
import javax.inject.Inject

class LogoutByFirebaseUseCase @Inject constructor(private val userRepository: AuthRepository) {
    operator fun invoke()= userRepository.logout()
}