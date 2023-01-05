package com.example.learningblibli.core.domain.usecase.impl

import com.example.learningblibli.core.data.repository.AuthRepository
import com.example.learningblibli.core.domain.usecase.contract.GetCurrentUserUseCase
import com.google.firebase.auth.FirebaseUser
import javax.inject.Inject

class GetCurrentUserUseCaseImpl @Inject constructor(private val authRepository: AuthRepository):GetCurrentUserUseCase {
    override operator fun invoke(): FirebaseUser? = authRepository.getCurrentUser()

}