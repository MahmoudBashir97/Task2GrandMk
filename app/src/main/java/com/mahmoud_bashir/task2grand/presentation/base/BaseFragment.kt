package com.mahmoud_bashir.task2grand.presentation.base

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import javax.inject.Inject

abstract class BaseFragment: Fragment(){


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initObservers()
        initErrorObserver()
        initLoading()
        initEvent()
    }

    abstract fun initEvent()

    abstract fun initObservers()

    abstract fun initLoading()

    abstract fun initErrorObserver()
}