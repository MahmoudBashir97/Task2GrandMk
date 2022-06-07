package com.mahmoud_bashir.task2grand.presentation.base

import android.os.Bundle
import android.os.PersistableBundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import javax.inject.Inject

abstract class BaseActivity: AppCompatActivity(){


    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        initEvent()
        initViewModel()
        initLoading()
        initObservers()
        initErrorObservers()
    }

    abstract fun initEvent()

    abstract fun initViewModel()

    abstract fun initObservers()

    abstract fun initErrorObservers()

    abstract fun initLoading()
}