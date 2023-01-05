package com.example.learningblibli.core.domain.usecase.impl

import com.example.learningblibli.core.data.repository.AuthRepository
import com.example.learningblibli.core.data.source.remote.Resource
import com.example.learningblibli.core.domain.usecase.contract.RegisterByFirebaseUseCase
import com.google.firebase.auth.FirebaseUser
import javax.inject.Inject

class RegisterByFirebaseUseCaseImpl @Inject constructor(private val userRepository: AuthRepository) : RegisterByFirebaseUseCase {
    override suspend operator fun invoke(email:String, password:String): Resource<FirebaseUser?> = userRepository.signUp(email,password)

}