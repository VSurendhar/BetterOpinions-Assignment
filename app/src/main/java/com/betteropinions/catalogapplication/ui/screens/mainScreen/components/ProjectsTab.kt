package com.betteropinions.catalogapplication.ui.screens.mainScreen.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.betteropinions.catalogapplication.R
import com.betteropinions.catalogapplication.ui.theme.InterFontFamily
import com.betteropinions.catalogapplication.ui.theme.catalogColors

@Composable
fun ProjectsTab(modifier: Modifier) {
    val colors = MaterialTheme.catalogColors
    Column(modifier = modifier
        .fillMaxSize()
        .background(Color.White)) {

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .background(colors.purple),
            contentAlignment = Alignment.CenterStart
        ) {
            Text(
                text = "My Projects",
                color = Color.White,
                fontSize = 18.sp,
                fontFamily = InterFontFamily,
                fontWeight = FontWeight.W500,
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .padding(bottom = 16.dp)
            )
        }

        // 🔷 Content
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Icon(
                    painter = painterResource(R.drawable.ic_empty),
                    contentDescription = null,
                    modifier = Modifier.size(24.dp)
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "Empty",
                    color = Color.Gray,
                    fontSize = 18.sp
                )
            }
        }
    }
}
