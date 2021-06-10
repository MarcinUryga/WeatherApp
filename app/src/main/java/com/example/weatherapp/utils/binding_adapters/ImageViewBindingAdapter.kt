package com.example.weatherapp.utils.binding_adapters

import android.graphics.drawable.Drawable
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.example.weatherapp.extensions.load

@BindingAdapter(
    value = ["loadUrl", "placeholder", "error"],
    requireAll = false
)
fun ImageView.loadUrl(
    url: String,
    placeholder: Drawable?,
    error: Drawable?,
) {
    load(
        url = url,
        placeholder = placeholder,
        error = error
    )
}