package com.example.marsestatedata.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.marsestatedata.R
import com.example.marsestatedata.bindImage
import com.example.marsestatedata.databinding.FragmentDetailBinding
import com.example.marsestatedata.overview.OverviewViewModel

/**
 * This [Fragment] will show the detailed information about a selected piece of Mars real estate.
 */
class DetailFragment : Fragment() {
    lateinit var binding: FragmentDetailBinding
    private val viewModel: DetailViewModel by lazy {
        ViewModelProvider(this).get(DetailViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentDetailBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val application = activity?.application // or requireNotNull(activity).application

        viewModel.selectedProperty.observe(viewLifecycleOwner){
            bindImage(binding.mainPhotoImage, it.imgSrcUrl)
            binding.propertyTypeText.text = it.type
            binding.priceValueText.text = it.price.toString()

        }

    }
}