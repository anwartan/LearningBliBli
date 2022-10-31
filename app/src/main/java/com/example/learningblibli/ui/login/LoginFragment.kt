package com.example.learningblibli.ui.login

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.learningblibli.R
import com.example.learningblibli.core.base.BaseFragment
import com.example.learningblibli.databinding.FragmentLoginBinding
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject

class LoginFragment : BaseFragment() {

    @Inject
    lateinit var factory: ViewModelProvider.Factory
    private val viewModel: AuthViewModel by activityViewModels {
        factory
    }
    private var _binding: FragmentLoginBinding? = null
    private val binding get()=_binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLoginBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.etEmail.setText("anwartan55@gmail.com")
        binding.etPassword.setText("123456")

        setupRegisterButton()
        setupLoginButton()
    }



    private fun setupLoginButton() {

        with(binding.btnLogin){
            setOnClickListener {
                if(!isInputValid()){
                    return@setOnClickListener
                }
                viewModel.loginUser(binding.etEmail.text.toString(),binding.etPassword.text.toString())
            }
        }

    }

    private fun isInputValid(): Boolean {
        with(binding.etEmail){
            if(text.isNullOrEmpty()){
                showToast("Email is required")
                return false
            }
        }
        with(binding.etPassword){
            if(text.isNullOrEmpty()){
                showToast("Password is required")
                return false
            }
        }
        return true
    }

    private fun setupRegisterButton() {
        with(binding.btnRegister){
            setOnClickListener {
                findNavController().navigate(R.id.registerFragment)
            }
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding=null
    }


}