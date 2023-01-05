package com.example.learningblibli.feature_detail.ui

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.content.res.AppCompatResources
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.example.learningblibli.core.base.BaseFragment
import com.example.learningblibli.core.data.source.remote.Resource
import com.example.learningblibli.feature_detail.R
import com.example.learningblibli.feature_detail.databinding.FragmentDetailBinding
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject

class DetailFragment : BaseFragment() {
    private var _binding : FragmentDetailBinding? = null
    private val binding get() = _binding!!
    private lateinit var detailViewModel: DetailViewModel
    @Inject
    lateinit var factory: ViewModelProvider.Factory

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailBinding.inflate(inflater,container,false)

        return binding.root
    }

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        activity?.let {
//           (it.application as DetailComponentProvider).provideDetailComponent().inject(this)
//        }
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
        detailViewModel.isFavorite.observe(viewLifecycleOwner){status->
            context?.let {
                if(status){
                    binding.btnFavorite.icon = AppCompatResources.getDrawable(it,R.drawable.ic_baseline_check_24)
                }else{
                    binding.btnFavorite.icon = AppCompatResources.getDrawable(it,R.drawable.ic_baseline_add_24)
                }
            }
        }

    }

    private fun getArgumentMeal() {
        val mealArg: Int? = arguments?.getString(MEAL)?.toInt()
        mealArg?.let {
            detailViewModel.getDetailMeal(it)
            detailViewModel.getFavoriteMeal(it)
        }
    }

    private fun setupDetailViewModel() {
        detailViewModel = ViewModelProvider(this, factory)[DetailViewModel::class.java]
    }

    private fun showDetailMeal(data: com.example.learningblibli.lib_model.model.Meal) {
        data.let {meal->
            Glide.with(this)
                .load(meal.strMealThumb)
                .into(binding.ivPoster)
            binding.tvDescription.text =meal.strInstructions
            binding.toolbarDetail.title =meal.strMeal
            binding.btnFavorite.setOnClickListener {
                detailViewModel.setFavoriteMeal(meal)
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