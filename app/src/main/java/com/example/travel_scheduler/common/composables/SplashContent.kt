package com.example.travel_scheduler.common.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import kotlinx.coroutines.delay
import com.example.travel_scheduler.R
import com.example.travel_scheduler.common.utils.colors.Theme
import com.example.travel_scheduler.common.utils.dimen.Dimensions
import com.example.travel_scheduler.common.utils.strings.AppStrings

@Composable
fun SplashContent(onTimeout: () -> Unit) {
    Box(
        modifier = Modifier.fillMaxSize()
            .background(Theme.BASE_APP_WHITE),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.plane),
            contentDescription = AppStrings.SPLASH_CONTENT_DESC,
            modifier = Modifier.size(Dimensions.SPLASH_IMAGE_SIZE)
        )
    }

    LaunchedEffect(Unit) {
        delay(2000)
        onTimeout()
    }
}