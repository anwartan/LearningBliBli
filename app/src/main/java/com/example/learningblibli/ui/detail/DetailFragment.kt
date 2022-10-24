package com.example.learningblibli.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.example.learningblibli.MyApplication
import com.example.learningblibli.R
import com.example.learningblibli.base.BaseFragment
import com.example.learningblibli.data.source.remote.Resource
import com.example.learningblibli.data.source.sharedpreferences.AppSharedPreferences
import com.example.learningblibli.databinding.FragmentDetailBinding
import com.example.learningblibli.domain.model.Meal
import com.example.learningblibli.utils.Constants
import javax.inject.Inject

class DetailFragment : BaseFragment() {
    private var _binding : FragmentDetailBinding? = null
    private val binding get() = _binding!!
    private lateinit var detailViewModel:DetailViewModel
    @Inject
    lateinit var factory: ViewModelProvider.Factory

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailBinding.inflate(inflater,container,false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (requireActivity().application as MyApplication).appComponent.inject(this)
        setupToolBar()
        setupDetailViewModel()
        getArgumentMeal()
        getObserveDetailMeal()
    }



    private fun setupToolBar() {
        with(binding.toolbarDetail){
            setNavigationIcon(R.drawable.ic_baseline_arrow_back_24)
            setNavigationOnClickListener { view ->
                findNavController().popBackStack()
            }
        }
    }

    private fun getObserveDetailMeal() {
        detailViewModel.detailMovie.observe(viewLifecycleOwner){
            when(it){

                is Resource.Success->{
                    it.data?.let {meal->
                        showDetailMeal(meal)
                    }
                }
            }
        }

    }

    private fun getArgumentMeal() {
        val meal:Meal? = arguments?.getParcelable(MEAL)
        meal?.let {
            detailViewModel.setMeal(it)
            showDetailMeal(it)
        }
    }

    private fun setupDetailViewModel() {
        detailViewModel = ViewModelProvider(this, factory)[DetailViewModel::class.java]
    }

    private fun showDetailMeal(data: Meal) {
        data.let {
            Glide.with(this)
                .load(it.strMealThumb)
                .into(binding.ivPoster)
            binding.tvDescription.text =it.strInstructions
            binding.toolbarDetail.title =it.strMeal
            binding.btnFavorite.setOnClickListener {
                detailViewModel.setFavoriteMovie()
            }
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding=null
    }

    companion object{
        const val MEAL = "MEAL"
    }
}