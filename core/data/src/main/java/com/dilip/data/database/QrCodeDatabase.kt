package com.dilip.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.dilip.domain.models.QrCode

@Database(
    entities = [QrCode::class],
    version = 1
)
abstract class QrCodeDatabase: RoomDatabase() {

    abstract val qrCode: QrDao

    companion object {
        const val DATABASE_NAME = "qr_codes_db"
    }
}