package com.example.learningblibli.core.data.repository

import com.example.learningblibli.core.data.source.remote.Resource
import com.example.learningblibli.core.domain.repository.IUserRepository
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.suspendCancellableCoroutine
import javax.inject.Inject
import kotlin.coroutines.resumeWithException

@OptIn(ExperimentalCoroutinesApi::class)
class AuthRepository @Inject constructor (private val firebaseAuth: FirebaseAuth) :
    IUserRepository {
    override suspend fun signIn(email: String, password: String): Resource<FirebaseUser?> {
       return try {
           val result = suspendCancellableCoroutine { cont->
               firebaseAuth.signInWithEmailAndPassword(email,password)
                   .addOnCompleteListener {
                       if (it.exception != null) {
                           cont.resumeWithException(it.exception!!)
                       } else {

                           it.result?.let { it1 -> cont.resume(it1, null) }
                       }
                   }
           }
            val user = result.user
            return if(user==null){
                Resource.Error("RESULT EMPTY")
            }else{
                Resource.Success(user)
            }

        }catch (e:Exception){
            Resource.Error(e.message?:"FAILED")
        }
    }

    override suspend fun signUp(email: String, password: String): Resource<FirebaseUser?> {
        return try {
            val result = suspendCancellableCoroutine { cont->
                firebaseAuth.createUserWithEmailAndPassword(email,password)
                    .addOnCompleteListener {
                        if (it.exception != null) {
                            cont.resumeWithException(it.exception!!)
                        } else {
                            it.result?.let { it1 -> cont.resume(it1, null) }
                        }
                    }
            }
            val user = result.user
            return if(user==null){
                Resource.Error("RESULT EMPTY")
            }else{
                Resource.Success(user)
            }
        }catch (e:Exception){
            Resource.Error(e.message?:"FAILED")
        }
    }

    override fun logout(){
        firebaseAuth.signOut()
    }

    override fun getCurrentUser():FirebaseUser? =  firebaseAuth.currentUser

}

