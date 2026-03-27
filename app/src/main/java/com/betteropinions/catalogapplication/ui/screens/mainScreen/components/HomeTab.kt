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
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.betteropinions.catalogapplication.R
import com.betteropinions.catalogapplication.ui.theme.InterFontFamily
import com.betteropinions.catalogapplication.ui.theme.catalogColors


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeTab(modifier: Modifier) {
    val colors = MaterialTheme.catalogColors
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Color.White)
    ) {

        // 🔷 Custom App Bar
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .background(colors.purple),
            contentAlignment = Alignment.CenterStart
        ) {
            Text(
                text = "Home",
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
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            contentPadding = PaddingValues(vertical = 16.dp)
        ) {
            items(4) {
                CatalogCard(
                    afterImageRes = R.drawable.img_catalog1_after,
                    beforeImageRes = R.drawable.img_catalog1_before
                )
            }
        }
    }
}
