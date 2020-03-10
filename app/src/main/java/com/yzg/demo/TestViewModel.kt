package com.yzg.demo

import androidx.lifecycle.LifecycleOwner
import com.yzg.basiclib.binding.onClick
import com.yzg.basiclib.network.retrofit.data.Optional
import com.yzg.basiclib.network.retrofit.exception.ApiException
import com.yzg.basiclib.network.retrofit.ext.bindLifecycle
import com.yzg.basiclib.network.retrofit.ext.sendCompose
import com.yzg.basiclib.network.retrofit.observer.HttpRxObserver
import com.yzg.basiclib.view.viewmodel.BaseViewModel
import com.yzg.demo.api.apiService

/**
 * Created by yangzhigang
 * on 2020/3/10
 *
 */
class TestViewModel : BaseViewModel(){
    val click : onClick = {
        when(it.id){
            R.id.start_new -> {
                startActivity(NewActivity :: class.java)
            }
        }
    }

    override fun onCreate(lifecycleOwner: LifecycleOwner) {
        super.onCreate(lifecycleOwner)
//        requestDemo()
    }

    /**
     * 网络请求示例
     * 可替换为自己的baseUrl api地址 参数 返回值类型等
     * */
    private fun requestDemo(){
        apiService.test().sendCompose().bindLifecycle(this).subscribe(
            object : HttpRxObserver<Optional<String>>(){
                override fun onError(e: ApiException) {

                }

                override fun onSuccess(data: Optional<String>) {
//                    data.includeNull    //可null值
//                    data.get()          //不可null值
                }

            }
        )

    }

}