package com.example.marsestatedata.overview

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.BindingAdapter
import androidx.lifecycle.ViewModelProvider
import com.example.marsestatedata.R
import com.example.marsestatedata.bindImage
import com.example.marsestatedata.bindRecycleView
import com.example.marsestatedata.databinding.FragmentOverviewBinding
import com.example.marsestatedata.databinding.GridViewItemBinding
import com.example.marsestatedata.network.MarsProperty

/**
 * This fragment shows the the status of the Mars real-estate web services transaction.
 */
class OverviewFragment : Fragment() {
    private lateinit var binding: FragmentOverviewBinding
    private val viewModel: OverviewViewModel by lazy {
        ViewModelProvider(this).get(OverviewViewModel::class.java)
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentOverviewBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Allows Data Binding to Observe LiveData with the lifecycle of this Fragment
        binding.lifecycleOwner = this

        // Giving the binding access to the OverviewViewModel
        /*binding.viewModel = viewModel*/

/*       viewModel.property.observe(viewLifecycleOwner){
            binding.textResponse.text = it.imgSrcUrl
        }*/


        binding.photosGrid.adapter = PhotoGridAdapter()

        viewModel.properties.observe(viewLifecycleOwner){
            bindRecycleView(recycleView = binding.photosGrid, data = it)
        }

    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.overflow_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

}
