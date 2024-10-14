package com.dilip.data.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import com.dilip.domain.models.QrCode

@Dao
interface QrDao {

    @Query("SELECT * FROM qrcode")
    fun getAllQrCodes(): List<QrCode>

    @Query("SELECT * FROM qrcode")
    fun getAllQrCodesFlow(): Flow<List<QrCode>>

    @Query("SELECT * FROM qrcode WHERE id = :id")
    suspend fun getQrCodeById(id: Int): QrCode?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertQrCode(qrCode: QrCode)

    @Delete
    suspend fun deleteQrCode(qrCode: QrCode)
}