package com.example.learningblibli.core.domain.usecase.impl

import com.example.learningblibli.core.data.repository.AuthRepository
import com.example.learningblibli.core.domain.usecase.contract.LogoutByFirebaseUseCase
import javax.inject.Inject

class LogoutByFirebaseUseCaseImpl @Inject constructor(private val userRepository: AuthRepository):LogoutByFirebaseUseCase{
    override operator fun invoke()= userRepository.logout()
}