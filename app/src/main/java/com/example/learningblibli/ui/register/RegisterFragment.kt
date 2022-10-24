package com.example.learningblibli.ui.register

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.learningblibli.MyApplication
import com.example.learningblibli.base.BaseFragment
import com.example.learningblibli.data.source.remote.Resource
import com.example.learningblibli.databinding.FragmentRegisterBinding
import com.example.learningblibli.ui.login.AuthViewModel
import javax.inject.Inject

class RegisterFragment : BaseFragment() {

    private lateinit var viewModel: AuthViewModel
    private var _binding:FragmentRegisterBinding?=null
    private val binding get() = _binding!!
    @Inject
    lateinit var factory: ViewModelProvider.Factory
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRegisterBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (requireActivity().application as MyApplication).appComponent.inject(this)
        setupRegisterViewModel()
        setupLoginButtonViewModel()
        setupRegisterButtonViewModel()
        getSignUpFlow()
    }

    private fun getSignUpFlow() {
        viewModel.signupFlow.observe(viewLifecycleOwner){
            when(it){
                is Resource.Success ->{
                    hideLoadingDialog()
                    it.data?.let {
                        Toast.makeText(context,"User created",Toast.LENGTH_SHORT).show()
                        findNavController().popBackStack()
                    }
                }
                is Resource.Error->{
                    hideLoadingDialog()
                    Toast.makeText(context,it.message?:"ERROR", Toast.LENGTH_SHORT).show()
                }
                is Resource.Loading->{
                    showLoadingDialog()
                }
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

    private fun setupRegisterViewModel() {
        viewModel = ViewModelProvider(this,factory)[AuthViewModel::class.java]

    }

}