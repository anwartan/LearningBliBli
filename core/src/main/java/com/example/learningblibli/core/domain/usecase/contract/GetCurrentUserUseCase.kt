package com.example.learningblibli.core.domain.usecase.contract

import com.google.firebase.auth.FirebaseUser

interface GetCurrentUserUseCase {
    operator fun invoke(): FirebaseUser?
}