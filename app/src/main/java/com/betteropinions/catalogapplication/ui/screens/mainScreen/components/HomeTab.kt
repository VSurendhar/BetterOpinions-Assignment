package com.betteropinions.catalogapplication.ui.screens.mainScreen.components


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.betteropinions.catalogapplication.R


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeTab(modifier: Modifier) {
    Column(modifier = modifier
        .fillMaxSize()
        .background(Color.White)) {

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
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            contentPadding = PaddingValues(vertical = 16.dp)
        ) {
            // Repeat for as many catalog cards as needed
            items(4) {
                CatalogCard(
                    afterImageRes = R.drawable.img_catalog1_after,
                    beforeImageRes = R.drawable.img_catalog1_before
                )
            }
        }

    }
}



