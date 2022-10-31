package com.example.learningblibli.feature_setting.ui

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.learningblibli.feature_setting.databinding.FragmentSettingBinding
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject

class SettingFragment : Fragment() {

    private var _binding: FragmentSettingBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: SettingViewModel
    @Inject
    lateinit var factory : ViewModelProvider.Factory

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSettingBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this,factory)[SettingViewModel::class.java]

        binding.smDarkMode.setOnCheckedChangeListener { _, b ->
            viewModel.saveThemeSetting(b)
        }

        viewModel.getThemeSettings().observe(viewLifecycleOwner){
            binding.smDarkMode.isChecked=it
        }
    }



}