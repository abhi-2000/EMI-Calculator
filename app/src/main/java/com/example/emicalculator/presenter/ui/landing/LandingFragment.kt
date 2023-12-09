package com.example.emicalculator.presenter.ui.landing

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.emicalculator.R
import com.example.emicalculator.data.util.Constants
import com.example.emicalculator.databinding.FragmentLandingBinding
import com.example.emicalculator.presenter.ui.base.BaseViewModel
import com.example.emicalculator.presenter.ui.base.MainActivity

class LandingFragment : Fragment() {

    private lateinit var landingScreenAdapter: LandingScreenAdapter
    private lateinit var baseViewModel: BaseViewModel
    private lateinit var binding: FragmentLandingBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_landing, container, false)
        baseViewModel = (activity as MainActivity).viewModel
        landingScreenAdapter = (activity as MainActivity).landingScreenAdapter
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        uiOperations()
    }

    private fun uiOperations() {
        baseViewModel.loadAllEmiData() {
            if (it.isEmpty()) {
                binding.noEmiAvailableLayout.isVisible = true
                binding.ongoingEmiRecyclerview.isVisible = false
            } else {
                binding.noEmiAvailableLayout.isVisible = false
                binding.ongoingEmiRecyclerview.isVisible = true
                binding.ongoingEmiRecyclerview.adapter = landingScreenAdapter
                landingScreenAdapter.differ.submitList(it)
                landingScreenAdapter.notifyDataSetChanged()
            }
        }

        //click listener
        binding.addNewLoan.setOnClickListener {
            val bundle = Bundle().apply {
                putString(Constants.TYPE, Constants.NEW_LOAN_TYPE)
            }
            findNavController().navigate(
                R.id.action_landingFragment_to_calculateEmiFragment,
                bundle
            )
        }

        landingScreenAdapter.onClickListener {
            val bundle = Bundle().apply {
                putString(Constants.TYPE, Constants.UPCOMING)
                putString(Constants.ID, it.id.toString())
            }
            findNavController().navigate(R.id.action_landingFragment_to_upcomingEmiFragment, bundle)
        }

    }
}