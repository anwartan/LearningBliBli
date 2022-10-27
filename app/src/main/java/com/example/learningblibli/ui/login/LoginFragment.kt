package com.example.learningblibli.ui.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.learningblibli.MyApplication
import com.example.learningblibli.R
import com.example.learningblibli.core.base.BaseFragment
import com.example.learningblibli.databinding.FragmentLoginBinding
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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (requireActivity().application as MyApplication).appComponent.inject(this)
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
                Toast.makeText(context,"Email is required",Toast.LENGTH_SHORT).show()
                return false
            }
        }
        with(binding.etPassword){
            if(text.isNullOrEmpty()){
                Toast.makeText(context,"Password is required",Toast.LENGTH_SHORT).show()
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



}