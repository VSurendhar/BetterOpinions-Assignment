package com.betteropinions.catalogapplication.ui.screens.splashScreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.betteropinions.catalogapplication.R
import com.betteropinions.catalogapplication.ui.theme.PoppinsFontFamily
import com.betteropinions.catalogapplication.ui.theme.Purple
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun SplashScreen(onSplashComplete: () -> Unit) {
    val scope = rememberCoroutineScope()
    LaunchedEffect(Unit) {
        scope.launch {
            delay(2000)
            onSplashComplete()
        }
    }
    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Spacer(modifier = Modifier.weight(1f))
            Image(
                modifier = Modifier.padding(vertical = 32.dp),
                painter = painterResource(R.drawable.ic_splash_logo),
                contentDescription = "Splash Logo"
            )
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = "Catalog",
                fontSize = 28.sp,
                color = Purple,
                fontFamily = PoppinsFontFamily,
                fontWeight = FontWeight.Medium,
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.weight(1f))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 32.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Made in India",
                    textAlign = TextAlign.Center,
                    color = Color(0xFF677183),
                    fontFamily = PoppinsFontFamily,
                    fontWeight = FontWeight.W400,
                )
                Spacer(modifier = Modifier.size(4.dp))
                Image(
                    modifier = Modifier.size(width = 16.dp, height = 11.dp),
                    painter = painterResource(R.drawable.img_india),
                    contentDescription = "India Flag"
                )
            }
        }
    }
}
