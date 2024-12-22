package com.dilip.data.remote

import com.dilip.data.remote.mapper.toDeviceQr
import com.dilip.data.remote.mapper.toMap
import com.dilip.domain.models.device.DeviceQr
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow

class FirebaseDeviceQrDataSource(
    private val firestore: FirebaseFirestore,
) {
    private val collection = firestore.collection("device_qrs")

    suspend fun addDeviceQr(deviceQr: DeviceQr) {
        collection.document(deviceQr.id.toString()).set(deviceQr.toMap())
    }

    suspend fun updateDeviceQr(deviceQr: DeviceQr) {
        collection.document(deviceQr.id.toString()).set(deviceQr.toMap())
    }

    suspend fun deleteDeviceQr(deviceId: Int) {
        collection.document(deviceId.toString()).delete()
    }

    fun getAllDeviceQrs(): Flow<List<DeviceQr>> = callbackFlow {
        val listener = collection.addSnapshotListener { snapshot, error ->
            if (error != null) {
                close(error)
                return@addSnapshotListener
            }

            val deviceQrs = snapshot?.documents?.mapNotNull { it.toDeviceQr() } ?: emptyList()
            trySend(deviceQrs)
        }

        awaitClose { listener.remove() }
    }

    fun getDeviceQrById(id: Int): Flow<DeviceQr?> = callbackFlow {
        val listener = collection.document(id.toString()).addSnapshotListener { snapshot, error ->
            if (error != null) {
                close(error)
                return@addSnapshotListener
            }

            val deviceQr = snapshot?.toDeviceQr()
            trySend(deviceQr)
        }

        awaitClose { listener.remove() }
    }
}
