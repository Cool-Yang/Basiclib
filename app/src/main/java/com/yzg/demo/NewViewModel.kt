package com.yzg.demo

import com.yzg.basiclib.binding.onClick
import com.yzg.basiclib.view.viewmodel.BaseViewModel

/**
 * Created by yangzhigang
 * on 2020/3/10
 *
 */

class NewViewModel : BaseViewModel(){
    val click : onClick = {
        when(it.id){
            R.id.close_new -> {
                finishEvent.call()
            }
        }
    }

}