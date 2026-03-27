package com.betteropinions.catalogapplication.ui.screens.mainScreen.components

import androidx.compose.foundation.BorderStroke
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
import com.betteropinions.catalogapplication.ui.dialogs.PaywallDialog
import com.betteropinions.catalogapplication.ui.dialogs.ThanksDialog
import com.betteropinions.catalogapplication.ui.theme.DarkSlateGrayBlue
import com.betteropinions.catalogapplication.ui.theme.InterFontFamily
import com.betteropinions.catalogapplication.ui.theme.PoppinsFontFamily
import com.betteropinions.catalogapplication.ui.theme.SlateGrayBlue
import com.betteropinions.catalogapplication.ui.theme.catalogColors

@Composable
fun CatalogCard(
    afterImageRes: Int,
    beforeImageRes: Int,
) {
    val colors = MaterialTheme.catalogColors
    val slides = listOf(afterImageRes, beforeImageRes)
    val labels = listOf("After", "Before")
    val pagerState = rememberPagerState(pageCount = { slides.size })
    var paywallDialog: Boolean by remember { mutableStateOf(false) }
    var thanksDialog: Boolean by remember { mutableStateOf(false) }

    Card(
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        modifier = Modifier.fillMaxWidth(),
        border = BorderStroke(1.dp, Color(0xFFEEEFF1)),
    ) {
        Column {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(horizontal = 12.dp, vertical = 12.dp)
            ) {
                Image(
                    painter = painterResource(R.drawable.ic_splash_logo),
                    contentDescription = null,
                    modifier = Modifier.size(24.dp)
                )
                Spacer(Modifier.width(8.dp))
                Text(
                    text = "Catalog",
                    fontFamily = PoppinsFontFamily,
                    fontWeight = FontWeight.W500,
                    color = DarkSlateGrayBlue,
                    fontSize = 16.sp,
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
                            shape = RoundedCornerShape(8.dp),
                            color = Color.White.copy(alpha = 0.85f),
                            modifier = Modifier
                                .padding(16.dp)
                                .align(Alignment.TopStart)
                        ) {
                            Text(
                                text = labels[pagerState.currentPage],
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Medium,
                                color = SlateGrayBlue,
                                fontFamily = InterFontFamily,
                                modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                            )
                        }
                    }
                }

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
                                    if (isSelected) colors.purpleLight
                                    else Color.White.copy(alpha = 0.6f)
                                )
                        )
                    }
                }
            }

            Button(
                onClick = {
                    paywallDialog = true
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(12.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = colors.purple
                ),
                shape = RoundedCornerShape(8.dp)
            ) {
                Text("TRY IT OUT!", fontSize = 16.sp, color = Color.White)
            }
        }

        if (paywallDialog) {
            PaywallDialog(onDismiss = {
                paywallDialog = false
            }, onPayClick = {
                paywallDialog = false
                thanksDialog = true
            })
        }

        if (thanksDialog) {
            ThanksDialog(onDismiss = {
                thanksDialog = false
            })
        }

    }
}