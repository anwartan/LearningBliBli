package com.example.learningblibli.core.data.repository

import com.example.learningblibli.core.data.source.remote.Resource
import com.example.learningblibli.core.domain.repository.IUserRepository
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.suspendCancellableCoroutine
import javax.inject.Inject
import kotlin.coroutines.resumeWithException


class AuthRepository @Inject constructor (private val firebaseAuth: FirebaseAuth) :
    IUserRepository {
    override suspend fun signIn(email: String, password: String): Resource<FirebaseUser?> {
        return try {
            val result = suspendCancellableCoroutine<AuthResult>{ cont->
                firebaseAuth.signInWithEmailAndPassword(email,password)
                    .addOnCompleteListener {
                        if (it.exception != null) {
                            cont.resumeWithException(it.exception!!)
                        } else {

                            it.result?.let { it1 -> cont.resume(it1, null) }
                        }
                    }
            }
            Resource.Success(result.user)
        }catch (e:Exception){
            Resource.Error(e.message?:"FAILED")
        }
    }

    override suspend fun signUp(email: String, password: String): Resource<FirebaseUser?> {
        return try {
            val result = suspendCancellableCoroutine<AuthResult>{cont->
                firebaseAuth.createUserWithEmailAndPassword(email,password)
                    .addOnCompleteListener {
                        if (it.exception != null) {
                            cont.resumeWithException(it.exception!!)
                        } else {

                            it.result?.let { it1 -> cont.resume(it1, null) }
                        }
                    }
            }
            Resource.Success(result.user)
        }catch (e:Exception){
            Resource.Error(e.message?:"FAILED")
        }
    }

    override fun logout(){
        firebaseAuth.signOut()
    }

    override fun getCurrentUser():FirebaseUser? = firebaseAuth.currentUser


}