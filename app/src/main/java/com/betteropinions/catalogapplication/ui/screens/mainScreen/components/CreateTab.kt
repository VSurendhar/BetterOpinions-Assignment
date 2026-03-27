package com.betteropinions.catalogapplication.ui.screens.mainScreen.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.betteropinions.catalogapplication.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreateTab(modifier: Modifier) {
    Column(modifier = modifier.fillMaxSize().background(Color.White)) {

        // 🔷 Custom App Bar
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp)
                .background(Color(0xFF5C2D91)),
            contentAlignment = Alignment.CenterStart
        ) {
            Text(
                text = "My Projects",
                color = Color.White,
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier
                    .padding(start = 16.dp)
            )
        }

        // 🔷 Content
        Box(
            modifier = Modifier
                .fillMaxSize(),
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
