package com.example.marsestatedata.overview

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.lifecycle.ViewModelProvider
import com.example.marsestatedata.R
import com.example.marsestatedata.databinding.FragmentOverviewBinding
import com.example.marsestatedata.databinding.GridViewItemBinding

/**
 * This fragment shows the the status of the Mars real-estate web services transaction.
 */
class OverviewFragment : Fragment() {
    private lateinit var binding: GridViewItemBinding
    private val viewModel: OverviewViewModel by lazy {
        ViewModelProvider(this).get(OverviewViewModel::class.java)
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = GridViewItemBinding.inflate(layoutInflater)
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


    viewModel.property.observe(viewLifecycleOwner){
        bindImage(binding.marsImage, it.imgSrcUrl)
    }
}

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.overflow_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

}
