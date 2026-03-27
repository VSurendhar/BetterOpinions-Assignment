package com.betteropinions.catalogapplication.ui.screens.onBoardScreen.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.betteropinions.catalogapplication.R

@Composable
fun BeforeAfterImageSection(
    @DrawableRes beforeImageRes: Int,
    @DrawableRes afterImageRes: Int,
    modifier: Modifier = Modifier,
    beforeLabel: String = "BEFORE",
    afterLabel: String = "AFTER",
    afterWidth: Dp = 258.dp,
    afterHeight: Dp = 373.dp,
    beforeWidth: Dp = 174.8.dp,
    beforeHeight: Dp = 218.5.dp,
    cornerRadius: Dp = 16.dp,
    beforeStrokeWidth: Dp = 10.dp,
) {
    val beforeOverhang = beforeWidth * 0.45f
    val totalWidth = beforeOverhang + afterWidth

    Box(
        modifier = modifier
            .width(totalWidth)
            .height(afterHeight),
    ) {
        Box(
            modifier = Modifier
                .size(width = afterWidth, height = afterHeight)
                .align(Alignment.TopEnd)
                .clip(RoundedCornerShape(cornerRadius)),
        ) {
            AsyncImage(
                model = afterImageRes,
                contentDescription = "After image",
                modifier = Modifier.matchParentSize(),
                contentScale = ContentScale.Crop,
            )

            Text(
                text = afterLabel,
                color = Color.White,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(end = 16.dp, bottom = 16.dp),
            )
        }

        Box(
            modifier = Modifier
                .size(width = beforeWidth, height = beforeHeight)
                .align(Alignment.BottomStart)
                .clip(RoundedCornerShape(cornerRadius))
                .border(
                    border = BorderStroke(beforeStrokeWidth, Color.White),
                    shape = RoundedCornerShape(cornerRadius),
                ),
        ) {
            AsyncImage(
                model = beforeImageRes,
                contentDescription = "Before image",
                modifier = Modifier.matchParentSize(),
                contentScale = ContentScale.Crop,
            )

            Text(
                text = beforeLabel,
                color = Color.White,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(bottom = 14.dp),
            )
        }
    }
}

@Composable
@Preview(showBackground = true)
fun PreviewBeforeAfterImageSection() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        BeforeAfterImageSection(
            beforeImageRes = R.drawable.img_onboard1_before,
            afterImageRes = R.drawable.img_onboard1_after,
        )
    }
}