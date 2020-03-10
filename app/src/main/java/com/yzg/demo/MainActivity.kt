package com.yzg.demo

import com.yzg.basiclib.view.activity.BaseModelActivity
import com.yzg.demo.databinding.ActivityMainBinding

class MainActivity : BaseModelActivity<ActivityMainBinding, TestViewModel>() {
    override fun initVariableId(): Int = BR.viewModel

    override fun initContentView(): Int = R.layout.activity_main

}
