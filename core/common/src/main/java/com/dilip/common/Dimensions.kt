package com.dilip.common

import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

val SmallSpacing = 4.dp
val MediumSpacing = 8.dp
val LargeSpacing = 16.dp

val ExtraLargeSpacing = 24.dp
val SmallElevation = 2.dp
val MediumElevation = 4.dp

val ButtonHeight = 52.dp

data class Dimensions(
    val default: Dp = 0.dp,
    val spaceExtraSmall: Dp = 4.dp,
    val spaceSmall: Dp = 8.dp,
    val spaceMedium: Dp = 16.dp,
    val spaceLarge: Dp = 32.dp,
    val spaceExtraLarge: Dp = 64.dp,
)

val LocalSpacing = compositionLocalOf { Dimensions() }
