package com.example.travel_scheduler.popup

import android.annotation.SuppressLint
import android.widget.PopupMenu


class IconPopupProvider {
    @SuppressLint("DiscouragedPrivateApi")
    fun iconProvider(popup: PopupMenu){
        val fieldMPopup = PopupMenu::class.java.getDeclaredField("mPopup")
        fieldMPopup.isAccessible = true
        val mPopup = fieldMPopup.get(popup)
        mPopup.javaClass.getDeclaredMethod("setForceShowIcon",Boolean::class.java)
            .invoke(mPopup,true)
    }
}