package com.example.learningblibli.ui.register

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.learningblibli.core.base.BaseFragment
import com.example.learningblibli.core.data.source.remote.Resource
import com.example.learningblibli.databinding.FragmentRegisterBinding
import com.example.learningblibli.ui.login.AuthViewModel
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject

class RegisterFragment : BaseFragment() {


    private var _binding:FragmentRegisterBinding?=null
    private val binding get() = _binding!!
    @Inject
    lateinit var factory: ViewModelProvider.Factory
    private val viewModel: AuthViewModel by activityViewModels {
        factory
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRegisterBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupLoginButtonViewModel()
        setupRegisterButtonViewModel()
        getSignUpFlow()
    }

    private fun getSignUpFlow() {
        viewModel.signupFlow.observe(viewLifecycleOwner){
            when(it){
                is Resource.Success ->{
                    it.data?.let {
                        showToast("User created")
                        findNavController().popBackStack()
                    }
                }

                else -> {}
            }
        }

    }

    private fun setupRegisterButtonViewModel() {

        with(binding.btnRegister){
            setOnClickListener {
                viewModel.register(binding.etEmail.text.toString(),binding.etPassword.text.toString())

            }
        }
    }

    private fun setupLoginButtonViewModel() {
        with(binding.btnLogin){
            setOnClickListener {
                findNavController().popBackStack()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding=null
    }


}