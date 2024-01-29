package com.example.marsestatedata.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.ViewModelFactoryDsl
import com.example.marsestatedata.R
import com.example.marsestatedata.bindImage
import com.example.marsestatedata.databinding.FragmentDetailBinding
import com.example.marsestatedata.overview.OverviewViewModel

/**
 * This [Fragment] will show the detailed information about a selected piece of Mars real estate.
 */
class DetailFragment : Fragment() {
    lateinit var binding: FragmentDetailBinding
/*    private val viewModel: DetailViewModel by lazy {
        ViewModelProvider(this).get(DetailViewModel::class.java)
    }*/

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentDetailBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val application = activity?.application // or requireNotNull(activity).application
        binding.lifecycleOwner = this

        val marsProperty = DetailFragmentArgs.fromBundle(requireArguments()).selectedProperty
        val viewModelFactory = application?.let { DetailViewModelFactory(marsProperty, it) }

        val viewModel: DetailViewModel = ViewModelProvider(this, viewModelFactory!!).get(DetailViewModel::class.java)


        viewModel.selectedProperty.observe(viewLifecycleOwner){
            bindImage(binding.mainPhotoImage, it.imgSrcUrl)
            binding.propertyTypeText.text = viewModel.displayPropertyType.value
            binding.priceValueText.text = viewModel.displayPropertyPrice.value

        }



    }
}