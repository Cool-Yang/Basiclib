package com.yzg.basiclib.binding

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.request.RequestOptions
import com.yzg.basiclib.image.GlideApp

/**
 * Created by yangzhigang
 * on 2020/3/9
 * 图片通用事件绑定封装
 */

@BindingAdapter("bind_res")
fun setImageRes(imageView: ImageView, resId: Int) {
    imageView.setImageResource(resId)
}

@BindingAdapter("bind_imageUrl_circle")
fun loadImageCircle(imageView: ImageView, url: String?) {
    GlideApp.with(imageView.context)
        .load(url)
        .apply(RequestOptions().circleCrop())
        .into(imageView)
}

@BindingAdapter("bind_imageUrl_centerCrop")
fun loadImagecenterCrop(imageView: ImageView, url: String?) {
    GlideApp.with(imageView.context)
        .load(url)
        .centerCrop()
        .into(imageView)
}

@BindingAdapter("bind_image_byte")
fun loadImagebyte(imageView: ImageView, bytes: ByteArray?) {
    if (bytes != null) {
        GlideApp.with(imageView.context)
            .asBitmap()
            .load(bytes)
            .into(imageView)
    }

}
