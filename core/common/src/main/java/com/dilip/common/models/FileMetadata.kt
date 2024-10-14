package com.dilip.common.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable

@Parcelize
@Serializable
data class FileMetadata(
    val fileName: String,
    val mimeType: String,
    val fileSize: Long,
    val uri: String,
) : Parcelable
