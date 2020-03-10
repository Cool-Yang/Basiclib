package com.yzg.demo

import com.yzg.basiclib.view.activity.BaseModelActivity
import com.yzg.demo.databinding.ActivityNewBinding

/**
 * Created by yangzhigang
 * on 2020/3/10
 *
 */

class NewActivity : BaseModelActivity<ActivityNewBinding,NewViewModel>(){
    override fun initContentView(): Int = R.layout.activity_new

    override fun initVariableId(): Int = BR.viewModel

}