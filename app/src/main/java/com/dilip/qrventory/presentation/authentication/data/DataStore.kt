package com.dilip.qrventory.presentation.authentication.data

import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map

val Context.dataStore by preferencesDataStore("login_prefs")

suspend fun saveLoginState(context: Context, isLoggedIn: Boolean) {
    context.dataStore.edit { prefs ->
        prefs[booleanPreferencesKey("is_logged_in")] = isLoggedIn
    }
}

suspend fun getLoginState(context: Context): Boolean {
    val prefs = context.dataStore.data.map { it[booleanPreferencesKey("is_logged_in")] ?: false }
    return prefs.first()
}
