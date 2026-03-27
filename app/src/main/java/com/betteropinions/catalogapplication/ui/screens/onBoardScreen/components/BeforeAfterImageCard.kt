package com.betteropinions.catalogapplication.ui.screens.onBoardScreen.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
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
import java.nio.file.WatchEvent


/*@Composable
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
    val leftOffset = -(afterWidth / 2.9f)
    Box(
        modifier = modifier.size(leftOffset + afterWidth ,afterHeight),
        contentAlignment = Alignment.Center
    ) {
        Box(
            modifier = Modifier
                .size(width = afterWidth, height = afterHeight)
                .clip(RoundedCornerShape(cornerRadius))
//                .align(Alignment.TopEnd)
        ) {

            AsyncImage(
                model = afterImageRes,
                contentDescription = "After Image",
                modifier = Modifier.matchParentSize(),
                contentScale = ContentScale.Crop
            )

            Text(
                text = afterLabel,
                color = Color.White,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .offset(x = (-16).dp, y = (-16).dp)
            )
        }

        Box(
            modifier = Modifier
                .size(width = beforeWidth, height = beforeHeight)
                .align(Alignment.BottomStart)
                .offset(x = leftOffset, y = 0.dp)
                .clip(RoundedCornerShape(cornerRadius))
                .border(
                    border = BorderStroke(beforeStrokeWidth, Color.White),
                    shape = RoundedCornerShape(cornerRadius)
                )
        ) {
            AsyncImage(
                model = beforeImageRes,
                contentDescription = "Before Image",
                modifier = Modifier.matchParentSize(),
                contentScale = ContentScale.Crop
            )

            Text(
                text = beforeLabel,
                color = Color.White,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .offset(y = (-14).dp)
            )
        }
    }
}*/

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
    // How much of the BEFORE image sticks out to the left of the AFTER image
    val beforeOverhang = beforeWidth * 0.45f

    // Total width = overhang on the left + after image width
    val totalWidth = beforeOverhang + afterWidth

    Box(
        modifier = modifier
            .width(totalWidth)
            .height(afterHeight),
    ) {
        // ── AFTER image (large, anchored to the right) ───────────────────────
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

            // "AFTER" label — bottom-right corner
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

        // ── BEFORE image (smaller, anchored to bottom-left, overlaps AFTER) ──
        Box(
            modifier = Modifier
                .size(width = beforeWidth, height = beforeHeight)
                .align(Alignment.BottomStart)   // anchor to bottom-left of the parent
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

            // "BEFORE" label — bottom-center
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