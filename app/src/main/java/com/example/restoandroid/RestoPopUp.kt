package com.example.restoandroid

import android.app.Dialog
import android.os.Bundle
import android.view.Window
import android.widget.Adapter
import com.example.restoandroid.adapters.Restaurant_adapter


class RestoPopUp(
        private val adapter: Restaurant_adapter
) : Dialog(adapter.context) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.details_restaurants)
    }

}