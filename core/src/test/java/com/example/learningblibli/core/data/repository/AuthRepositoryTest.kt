package com.example.learningblibli.core.data.repository

import android.app.Activity
import com.example.learningblibli.core.data.source.remote.Resource
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.OnFailureListener
import com.google.android.gms.tasks.OnSuccessListener
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import java.util.concurrent.Executor

@OptIn(ExperimentalCoroutinesApi::class)
class AuthRepositoryTest {
    private lateinit var successTask: Task<AuthResult>
    private lateinit var failureTask: Task<AuthResult>

    @Mock
    private lateinit var firebaseAuth: FirebaseAuth

    @InjectMocks
    private lateinit var authRepository: AuthRepository

    private lateinit var fakeAuthResult: AuthResult
    private val dispatcher = UnconfinedTestDispatcher()

    @Before
    fun setUp() {

        fakeAuthResult = FakeAuthResult()

        MockitoAnnotations.openMocks(this)
        Dispatchers.setMain(dispatcher)

        successTask = object : Task<AuthResult>() {
            override fun isComplete(): Boolean = true
            override fun isSuccessful(): Boolean = true

            override fun addOnCompleteListener(p0: OnCompleteListener<AuthResult>): Task<AuthResult> {
                p0.onComplete(successTask)
                return successTask
            }

            override fun isCanceled(): Boolean {
                return false
            }

            override fun getResult(): AuthResult {
                return fakeAuthResult
            }

            override fun <X : Throwable?> getResult(p0: Class<X>): AuthResult {
                return fakeAuthResult
            }

            override fun getException(): Exception? {
                return null
            }

            override fun addOnSuccessListener(p0: OnSuccessListener<in AuthResult>): Task<AuthResult> {
                return successTask
            }

            override fun addOnSuccessListener(
                p0: Executor,
                p1: OnSuccessListener<in AuthResult>
            ): Task<AuthResult> {
                return successTask
            }

            override fun addOnSuccessListener(
                p0: Activity,
                p1: OnSuccessListener<in AuthResult>
            ): Task<AuthResult> {
                return successTask
            }

            override fun addOnFailureListener(p0: OnFailureListener): Task<AuthResult> {
                return successTask
            }

            override fun addOnFailureListener(
                p0: Executor,
                p1: OnFailureListener
            ): Task<AuthResult> {
                return successTask
            }

            override fun addOnFailureListener(
                p0: Activity,
                p1: OnFailureListener
            ): Task<AuthResult> {
                return successTask
            }
        }
        failureTask = object : Task<AuthResult>() {
            override fun addOnCompleteListener(p0: OnCompleteListener<AuthResult>): Task<AuthResult> {
                p0.onComplete(failureTask)
                return failureTask
            }

            override fun isComplete(): Boolean {
                return true
            }

            override fun isSuccessful(): Boolean {
                return false
            }

            override fun isCanceled(): Boolean {
                return true
            }

            override fun getResult(): AuthResult? {
                return null
            }

            override fun <X : Throwable?> getResult(p0: Class<X>): AuthResult? {
                return null
            }

            override fun getException(): Exception {
                return Exception("SERVER_ERROR")
            }

            override fun addOnSuccessListener(p0: OnSuccessListener<in AuthResult>): Task<AuthResult> {
                return failureTask
            }

            override fun addOnSuccessListener(
                p0: Executor,
                p1: OnSuccessListener<in AuthResult>
            ): Task<AuthResult> {
                return failureTask
            }

            override fun addOnSuccessListener(
                p0: Activity,
                p1: OnSuccessListener<in AuthResult>
            ): Task<AuthResult> {
                return failureTask
            }

            override fun addOnFailureListener(p0: OnFailureListener): Task<AuthResult> {
                return failureTask
            }

            override fun addOnFailureListener(
                p0: Executor,
                p1: OnFailureListener
            ): Task<AuthResult> {
                return failureTask
            }

            override fun addOnFailureListener(
                p0: Activity,
                p1: OnFailureListener
            ): Task<AuthResult> {
                return failureTask
            }

        }
    }

    @After
    fun tearDown() {
        Mockito.verifyNoMoreInteractions(firebaseAuth)
        Dispatchers.resetMain()

    }

    @Test
    fun signIn() = runTest(dispatcher) {
        val email = "admin@gmail.com"
        val password = "admin123"

        Mockito.`when`(firebaseAuth.signInWithEmailAndPassword(email, password))
            .thenReturn(successTask)
        val result = authRepository.signIn(email, password)
        Assert.assertTrue(result is Resource.Success)
        Assert.assertNotNull(result.data)
        Assert.assertEquals(fakeAuthResult.user?.uid, result.data?.uid)
        Mockito.verify(firebaseAuth).signInWithEmailAndPassword(email, password)

    }

    @Test
    fun signInFailed() = runTest(dispatcher) {
        val email = "admin@gmail.com"
        val password = "admin123"
        Mockito.`when`(firebaseAuth.signInWithEmailAndPassword(email, password))
            .thenReturn(failureTask)
        val result = authRepository.signIn(email, password)
        Assert.assertTrue(result is Resource.Error)
        Assert.assertEquals("SERVER_ERROR", result.message)
        Mockito.verify(firebaseAuth).signInWithEmailAndPassword(email, password)
    }

    @Test
    fun signUp() = runTest {
        val email = "admin@gmail.com"
        val password = "admin123"

        Mockito.`when`(firebaseAuth.createUserWithEmailAndPassword(email, password))
            .thenReturn(successTask)
        val result = authRepository.signUp(email, password)
        Assert.assertTrue(result is Resource.Success)
        Assert.assertNotNull(result.data)
        Assert.assertEquals(fakeAuthResult.user?.uid, result.data?.uid)
        Mockito.verify(firebaseAuth).createUserWithEmailAndPassword(email, password)
    }

    @Test
    fun signUpFailed() = runTest {
        val email = "admin@gmail.com"
        val password = "admin123"
        Mockito.`when`(firebaseAuth.createUserWithEmailAndPassword(email, password))
            .thenReturn(failureTask)
        val result = authRepository.signUp(email, password)
        Assert.assertTrue(result is Resource.Error)
        Assert.assertEquals("SERVER_ERROR", result.message)
        Mockito.verify(firebaseAuth).createUserWithEmailAndPassword(email, password)
    }

    @Test
    fun logout() {
        authRepository.logout()
        Mockito.verify(firebaseAuth).signOut()
    }

    @Test
    fun getCurrentUser() {
        val firebaseUser = Mockito.mock(FirebaseUser::class.java)
        Mockito.`when`(firebaseAuth.currentUser).thenReturn(firebaseUser)
        val result = firebaseAuth.currentUser
        Assert.assertNotNull(result)
        Mockito.verify(firebaseAuth).currentUser
    }
}