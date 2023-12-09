package com.example.emicalculator.presenter.ui.base

import android.graphics.Color
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.emicalculator.R
import com.example.emicalculator.presenter.ui.landing.LandingScreenAdapter
import com.example.emicalculator.presenter.ui.upcoming.UpComingEmiAdapter
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {


    @Inject
    lateinit var landingScreenAdapter: LandingScreenAdapter

    @Inject
    lateinit var upComingEmiAdapter: UpComingEmiAdapter

    @Inject
    lateinit var baseViewModelFactory: BaseViewModelFactory


    lateinit var viewModel: BaseViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)
        viewModel= ViewModelProvider(this,baseViewModelFactory)[BaseViewModel::class.java]
    }


}