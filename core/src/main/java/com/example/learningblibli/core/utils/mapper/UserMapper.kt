package com.example.learningblibli.core.utils.mapper

import com.example.learningblibli.lib_model.model.User
import com.google.firebase.auth.FirebaseUser

object UserMapper {
    fun mapFirebaseUserToUser(firebaseUser: FirebaseUser):User{

        return User(
            uid = firebaseUser.uid,
            email = firebaseUser.email,
            displayName = firebaseUser.displayName
        )
    }
}