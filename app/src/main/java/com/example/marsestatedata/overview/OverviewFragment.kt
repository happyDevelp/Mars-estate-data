package com.example.marsestatedata.overview

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.databinding.BindingAdapter
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.marsestatedata.R
import com.example.marsestatedata.bindImage
import com.example.marsestatedata.bindRecycleView
import com.example.marsestatedata.bindStatus
import com.example.marsestatedata.databinding.FragmentOverviewBinding
import com.example.marsestatedata.databinding.GridViewItemBinding
import com.example.marsestatedata.network.MarsApiFilter
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


        binding.photosGrid.adapter = PhotoGridAdapter(PhotoGridAdapter.OnClickListener {
            viewModel.displayPropertyDetails(it)
        })

        viewModel.navigateToSelectedProperty.observe(viewLifecycleOwner){
            if (it != null){
                findNavController().navigate(OverviewFragmentDirections.actionOverviewFragmentToDetailFragment(it))
                viewModel.displayPropertyDetailsComplete()
            }
        }

        viewModel.properties.observe(viewLifecycleOwner){
            bindRecycleView(recycleView = binding.photosGrid, data = it)
        }

        viewModel.status.observe(viewLifecycleOwner){
            bindStatus(binding.statusImage, it)
        }


        val menuHost: MenuHost = requireActivity()
        menuHost.addMenuProvider(menuProvider(), viewLifecycleOwner)

    }

    private fun menuProvider(): MenuProvider {
        return object : MenuProvider{
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                menuInflater.inflate(R.menu.overflow_menu, menu)
            }

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                viewModel.updateFilter(
                    when(menuItem.itemId) {
                        R.id.show_rent_menu -> MarsApiFilter.SHOW_RENT
                        R.id.show_buy_menu -> MarsApiFilter.SHOW_BUY
                        else -> MarsApiFilter.SHOW_ALL
                    }
                )
                return false
            }
        }
    }


}
