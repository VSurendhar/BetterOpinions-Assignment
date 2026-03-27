package com.betteropinions.catalogapplication.ui.screens.mainScreen.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.betteropinions.catalogapplication.R

@Composable
fun CatalogCard(
    afterImageRes: Int,
    beforeImageRes: Int,
) {
    val slides = listOf(afterImageRes, beforeImageRes)
    val labels = listOf("After", "Before")
    val pagerState = rememberPagerState(pageCount = { slides.size })

    Card(
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Column {
            // Header
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(horizontal = 12.dp, vertical = 8.dp)
            ) {
                Icon(
                    painter = painterResource(R.drawable.ic_splash_logo),
                    contentDescription = null,
                    modifier = Modifier.size(20.dp)
                )
                Spacer(Modifier.width(6.dp))
                Text(
                    text = "Catalog",
                    fontWeight = FontWeight.Medium,
                    fontSize = 14.sp,
                )
            }

            // Image Pager with Before/After label
            Box {
                HorizontalPager(
                    state = pagerState,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(450.dp)
                        .padding(horizontal = 12.dp),
                ) { page ->
                    Box {
                        Image(
                            painter = painterResource(id = slides[page]),
                            contentDescription = labels[page],
                            contentScale = ContentScale.FillBounds,
                            modifier = Modifier
                                .fillMaxSize()
                                .clip(RoundedCornerShape(12.dp))
                        )
                        Surface(
                            shape = RoundedCornerShape(4.dp),
                            color = Color.White.copy(alpha = 0.85f),
                            modifier = Modifier
                                .padding(16.dp)
                                .align(Alignment.TopStart)
                        ) {
                            Text(
                                text = labels[pagerState.currentPage],
                                fontSize = 12.sp,
                                fontWeight = FontWeight.Medium,
                                color = Color.Black,
                                modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                            )
                        }
                    }
                }

                // "After" / "Before" label — top-left

                // Dot indicators — bottom-center
                Row(
                    modifier = Modifier
                        .align(Alignment.BottomCenter)
                        .padding(bottom = 8.dp),
                    horizontalArrangement = Arrangement.spacedBy(6.dp),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    repeat(slides.size) { index ->
                        val isSelected = pagerState.currentPage == index
                        Box(
                            modifier = Modifier
                                .clip(CircleShape)
                                .size(if (isSelected) 10.dp else 6.dp)
                                .background(
                                    if (isSelected) Color(0xFF6B3FA0)
                                    else Color.White.copy(alpha = 0.6f)
                                )
                        )
                    }
                }
            }

            // Button
            Button(
                onClick = { },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(12.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF5C2D91)
                ),
                shape = RoundedCornerShape(8.dp)
            ) {
                Text("TRY IT OUT!", color = Color.White)
            }
        }
    }
}