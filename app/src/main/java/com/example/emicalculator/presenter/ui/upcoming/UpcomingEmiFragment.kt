package com.example.emicalculator.presenter.ui.upcoming

import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.addCallback
import androidx.annotation.RequiresApi
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.emicalculator.R
import com.example.emicalculator.data.db.entity.EmiEntity
import com.example.emicalculator.data.db.entity.UpComingEmiEntity
import com.example.emicalculator.data.util.Constants
import com.example.emicalculator.data.util.Dialogs
import com.example.emicalculator.databinding.FragmentUpcomingEmiBinding
import com.example.emicalculator.presenter.ui.base.BaseViewModel
import com.example.emicalculator.presenter.ui.base.MainActivity
import java.time.LocalDate

@RequiresApi(Build.VERSION_CODES.O)
class UpcomingEmiFragment : Fragment() {

    private var principalAmount = ""
    private var rateOfInterest = ""
    private var tenureInMonths = 0
    private var emi = ""
    private var newCreatedUpcomingEmiList = arrayListOf<UpComingEmiEntity>()
    private var parentEmiId = ""
    private lateinit var baseViewModel: BaseViewModel
    private lateinit var upComingEmiAdapter: UpComingEmiAdapter
    private lateinit var binding: FragmentUpcomingEmiBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_upcoming_emi, container, false)
        setUpObjects()
        return binding.root
    }

    private fun setUpObjects() {
        baseViewModel = (activity as MainActivity).viewModel
        upComingEmiAdapter = (activity as MainActivity).upComingEmiAdapter
    }


    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (requireArguments().getString(Constants.TYPE) == Constants.UPCOMING) {
            parentEmiId = requireArguments().getString(Constants.ID, "")
            if (parentEmiId.isEmpty()) {
                findNavController().popBackStack()
                return
            }

            //loads all saved content and related operations
            baseViewModel.loadAllUpcomingData(emiId = parentEmiId.toLong()) {
                binding.upcomingEmiRecyclerview.adapter = upComingEmiAdapter
                upComingEmiAdapter.differ.submitList(it)
                upComingEmiAdapter.notifyDataSetChanged()
            }

            upComingEmiAdapter.setOnClickListener { res, position ->
                Dialogs.showConfirmationDialog(
                    true,
                    requireActivity(),
                    false,
                    "Are you sure, you have paid the EMI ?"
                ) {
                    if (it) {
                        baseViewModel.updateUpcomingEmiData(res.id, true)
                        upComingEmiAdapter.differ.currentList[position].isPaid = true
                        upComingEmiAdapter.notifyItemChanged(position)
                    }
                }
            }

        } else {
            principalAmount = requireArguments().getString(Constants.PRINCIPAL_AMOUNT, "0")
            rateOfInterest = requireArguments().getString(Constants.RATE_OF_INTEREST, "0")
            tenureInMonths = requireArguments().getInt(Constants.TENURE, 0)
            emi = requireArguments().getString(Constants.EMI, "0")
            //loads new created content
            newCreatedUpcomingEmiList.clear()
            newCreatedUpcomingEmiList.addAll(
                baseViewModel.generateUpComingEMIPayments(
                    LocalDate.now().toString(), tenureInMonths, emi, 0
                )
            )
            binding.upcomingEmiRecyclerview.adapter = upComingEmiAdapter
            upComingEmiAdapter.differ.submitList(newCreatedUpcomingEmiList)
            upComingEmiAdapter.notifyDataSetChanged()


            upComingEmiAdapter.setOnClickListener { res, position ->
                Toast.makeText(requireContext(),"After you save the loan you can pay your EMI",Toast.LENGTH_LONG).show()
            }
        }



        handlePrivateBackPressEvent()

    }

    private fun handlePrivateBackPressEvent() {
        binding.ivBack.setOnClickListener {
            if (requireArguments().getString(Constants.TYPE) == Constants.NEW_LOAN_TYPE) {
                Dialogs.showConfirmationDialog(
                    true,
                    requireActivity(),
                    false,
                    "Are you sure, you want to save the EMI ?",
                ) {
                    if (it) {
                        //save to database
                        saveDataToDataBase()
                    }
                    findNavController().navigate(R.id.action_upcomingEmiFragment_to_landingFragment)
                }
            } else {
                findNavController().popBackStack()
            }
        }

        requireActivity().onBackPressedDispatcher.addCallback(this) {
            if (requireArguments().getString(Constants.TYPE) == Constants.NEW_LOAN_TYPE) {
                Dialogs.showConfirmationDialog(
                    true,
                    requireActivity(),
                    false,
                    "Are you sure, you want to save the EMI ?",
                ) {
                    if (it) {
                        //save to database
                        saveDataToDataBase()
                    }
                   findNavController().navigate(R.id.action_upcomingEmiFragment_to_landingFragment)
                }
            } else {
                findNavController().popBackStack()
            }
        }
    }


    private fun saveDataToDataBase() {
        var getRecentDataId: Long = 0
        baseViewModel.insertEmiData(
            EmiEntity(
                0,
                principalAmount,
                rateOfInterest,
                tenureInMonths.toString(),
                emiAmount = emi,
                LocalDate.now().toString()
            )
        ){
            baseViewModel.loadAllEmiData {
                getRecentDataId = it.maxOfOrNull { res -> res.id }!!
                baseViewModel.bulkInsertEmiData(
                    baseViewModel.generateUpComingEMIPayments(
                        LocalDate.now().toString(), tenureInMonths, emi = emi, emiId = getRecentDataId
                    )
                )
            }

        }
    }

}