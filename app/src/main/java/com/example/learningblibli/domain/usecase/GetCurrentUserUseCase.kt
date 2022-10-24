package com.example.learningblibli.domain.usecase

import androidx.lifecycle.LiveData
import com.example.learningblibli.data.repository.AuthRepository
import com.google.firebase.auth.FirebaseUser
import javax.inject.Inject

class GetCurrentUserUseCase @Inject constructor(private val authRepository: AuthRepository) {
    operator fun invoke(): LiveData<FirebaseUser?> = authRepository.getCurrentUser()

}