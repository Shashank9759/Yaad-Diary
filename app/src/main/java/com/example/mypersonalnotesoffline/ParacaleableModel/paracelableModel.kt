package com.example.mypersonalnotesoffline.ParacaleableModel

import android.os.Parcel
import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
data class paracelableModel(

    val id:Int,
    val title:String,
    val subtitle:String,
    val description :String,
    val date:String,
    val priority:Int
):Parcelable