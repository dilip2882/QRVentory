package com.dilip.data.database

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity
data class Device(
    @PrimaryKey val ipAddress: String,
    val deviceName: String,
    val deviceImage: String?,
) : Parcelable