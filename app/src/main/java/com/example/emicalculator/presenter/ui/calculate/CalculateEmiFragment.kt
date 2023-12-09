package com.example.emicalculator.presenter.ui.calculate

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SeekBar
import androidx.annotation.RequiresApi
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.emicalculator.R
import com.example.emicalculator.data.util.Constants
import com.example.emicalculator.databinding.FragmentCalculateEmiBinding
import com.example.emicalculator.presenter.ui.base.BaseViewModel
import com.example.emicalculator.presenter.ui.base.MainActivity

class CalculateEmiFragment : Fragment() {

    private lateinit var baseViewModel: BaseViewModel
    private lateinit var binding: FragmentCalculateEmiBinding

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_calculate_emi, container, false)
        baseViewModel = (activity as MainActivity).viewModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.ivBack.setOnClickListener { findNavController().popBackStack() }

        binding.tenureSeekbar.setOnSeekBarChangeListener(seekBarChangeListener)
        binding.amountSeekbar.setOnSeekBarChangeListener(seekBarChangeListener)
        binding.rateSeekbar.setOnSeekBarChangeListener(seekBarChangeListener)

        binding.emiProceed.setOnClickListener {
            val bundle = Bundle().apply {
                putString(Constants.TYPE, Constants.NEW_LOAN_TYPE)
                putString(Constants.PRINCIPAL_AMOUNT, binding.loanAmount.text.toString().trim())
                putString(Constants.RATE_OF_INTEREST, binding.loanRate.text.toString().trim())
                putInt(Constants.TENURE, binding.tenureSeekbar.progress)
                putString(Constants.EMI, binding.emiAmount.text.toString().trim())
            }
            findNavController().navigate(
                R.id.action_calculateEmiFragment_to_upcomingEmiFragment,bundle)
        }

    }

    private val seekBarChangeListener = object : SeekBar.OnSeekBarChangeListener {
        override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
            // Perform EMI calculation here and update the TextView
            if (seekBar == binding.amountSeekbar) {
                seekBar?.progress = calculateNearestMultiple(progress, 2500)
            }
            updateEmi()
        }

        override fun onStartTrackingTouch(seekBar: SeekBar?) {
            // Not needed for this example
        }

        override fun onStopTrackingTouch(seekBar: SeekBar?) {
            // Not needed for this example
        }
    }

    private fun calculateNearestMultiple(value: Int, multiple: Int): Int {
        return (value + multiple / 2) / multiple * multiple
    }

    private fun updateEmi() {
        binding.loanAmount.text = "₹ ${binding.amountSeekbar.progress}"
        binding.loanRate.text = "${binding.rateSeekbar.progress}%"
        binding.loanTenure.text = "${binding.tenureSeekbar.progress} months"
        val emi = baseViewModel.calculateEMI(
            binding.amountSeekbar.progress.toDouble(),
            binding.rateSeekbar.progress.toDouble(),
            binding.tenureSeekbar.progress
        )
        binding.emiAmount.text = String.format("₹%.2f", emi)
    }


}