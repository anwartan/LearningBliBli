package com.example.learningblibli.feature_detail.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.example.learningblibli.core.base.BaseFragment
import com.example.learningblibli.core.data.source.remote.Resource
import com.example.learningblibli.core.domain.model.Meal
import com.example.learningblibli.feature_detail.di.DetailComponentProvider
import com.example.learningblibli.feature_detail.R
import com.example.learningblibli.feature_detail.databinding.FragmentDetailBinding
import javax.inject.Inject

class DetailFragment : BaseFragment() {
    private var _binding : FragmentDetailBinding? = null
    private val binding get() = _binding!!
    private lateinit var detailViewModel: DetailViewModel
    @Inject
    lateinit var factory: ViewModelProvider.Factory

    private lateinit var meal: Meal
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailBinding.inflate(inflater,container,false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        activity?.let {
           (it.application as DetailComponentProvider).provideDetailComponent().inject(this)
        }
        setupToolBar()
        setupDetailViewModel()
        getArgumentMeal()
        getObserveDetailMeal()
    }



    private fun setupToolBar() {
        with(binding.toolbarDetail){
            setNavigationIcon(R.drawable.ic_baseline_arrow_back_24)
            setNavigationOnClickListener {
                findNavController().popBackStack()
            }
        }
    }

    private fun getObserveDetailMeal() {
        detailViewModel.detailMeal.observe(viewLifecycleOwner){
            when(it){
                is Resource.Success->{
                    it.data?.let {meal->
                        showDetailMeal(meal)
                    }
                }
                else -> {}
            }
        }

    }

    private fun getArgumentMeal() {
        val mealArg: Meal? = arguments?.getParcelable(MEAL)
        mealArg?.let {
            meal = it
            detailViewModel.getDetailMeal(it)
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
                detailViewModel.setFavoriteMovie(meal)
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