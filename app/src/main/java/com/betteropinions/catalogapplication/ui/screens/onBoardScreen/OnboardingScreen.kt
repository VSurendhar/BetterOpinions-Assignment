package com.betteropinions.catalogapplication.ui.screens.onBoardScreen

import android.app.Activity
import androidx.activity.compose.BackHandler
import androidx.annotation.DrawableRes
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.isImeVisible
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.view.WindowCompat
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.betteropinions.catalogapplication.R
import com.betteropinions.catalogapplication.ui.screens.onBoardScreen.components.BeforeAfterImageSection
import com.betteropinions.catalogapplication.ui.theme.DarkSlateGrayBlue
import com.betteropinions.catalogapplication.ui.theme.LavenderMist
import com.betteropinions.catalogapplication.ui.theme.Purple
import com.betteropinions.catalogapplication.ui.theme.SlateGrayBlue
import com.betteropinions.catalogapplication.ui.theme.catalogColors
import kotlinx.coroutines.delay

private const val SLIDE_DURATION = 350
private const val RESEND_COUNTDOWN = 30

data class BeforeAfterSlide(
    @DrawableRes val beforeImageRes: Int,
    @DrawableRes val afterImageRes: Int,
)

private enum class FormStep { ENTER_NUMBER, ENTER_OTP }

@OptIn(
    ExperimentalLayoutApi::class,
    ExperimentalAnimationApi::class,
    ExperimentalComposeUiApi::class
)
@Composable
fun OnboardingScreen(
    onOtpVerified: () -> Unit,
    viewModel: OnboardingViewModel = viewModel(),
) {
    val colors = MaterialTheme.catalogColors
    val keyboardController = LocalSoftwareKeyboardController.current
    val otpFocusRequester = remember { FocusRequester() }
    var formStep by remember { mutableStateOf(FormStep.ENTER_NUMBER) }
    var phoneNumber by remember { mutableStateOf("") }
    var otp by remember { mutableStateOf("") }
    var otpTimer by remember { mutableIntStateOf(RESEND_COUNTDOWN) }
    var canResend by remember { mutableStateOf(false) }

    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val slides = uiState.slides

    val pagerState = rememberPagerState(pageCount = { slides.size })

    LaunchedEffect(pagerState.pageCount) {
        while (true) {
            delay(2_000)
            val nextPage = (pagerState.currentPage + 1) % pagerState.pageCount
            pagerState.animateScrollToPage(nextPage)
        }
    }

    LaunchedEffect(formStep) {
        if (formStep == FormStep.ENTER_OTP) {
            otpTimer = RESEND_COUNTDOWN
            canResend = false
            while (otpTimer > 0) {
                delay(1_000)
                otpTimer--
            }
            canResend = true
        }
    }

    SetWhiteStatusBar()

    BackHandler(enabled = formStep == FormStep.ENTER_OTP) {
        formStep = FormStep.ENTER_NUMBER
    }

    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .background(Purple)
            .statusBarsPadding(),
    ) { paddingValues ->

        val isKeyboardOpen = WindowInsets.isImeVisible

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .background(Color.White)
                .verticalScroll(rememberScrollState())
                .imePadding(),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {

            AnimatedVisibility(
                visible = !isKeyboardOpen,
                enter = fadeIn(tween(100)) + expandVertically(
                    animationSpec = tween(100),
                    expandFrom = Alignment.Top,
                ),
                exit = fadeOut(tween(100)) + shrinkVertically(
                    animationSpec = tween(100),
                    shrinkTowards = Alignment.Top,
                ),
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 34.dp, bottom = 14.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    HorizontalPager(
                        state = pagerState,
                        modifier = Modifier
                            .fillMaxWidth()
                            .wrapContentHeight(),
                        contentPadding = PaddingValues(horizontal = 24.dp),
                        pageSpacing = 16.dp,
                    ) { page ->
                        BeforeAfterImageSection(
                            beforeImageRes = slides[page].beforeImageRes,
                            afterImageRes = slides[page].afterImageRes,
                            modifier = Modifier.fillMaxWidth(),
                        )
                    }

                    Spacer(Modifier.height(12.dp))

                    Row(
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
                                        else colors.purpleLight.copy(alpha = 0.3f)
                                    ),
                            )
                        }
                    }
                }
            }

            AnimatedContent(
                targetState = formStep,
                transitionSpec = {
                    if (targetState == FormStep.ENTER_OTP) {
                        (slideInHorizontally(tween(SLIDE_DURATION)) { it } + fadeIn(
                            tween(
                                SLIDE_DURATION
                            )
                        )).togetherWith(slideOutHorizontally(tween(SLIDE_DURATION)) { -it } + fadeOut(
                            tween(
                                SLIDE_DURATION
                            )
                        ))
                    } else {
                        (slideInHorizontally(tween(SLIDE_DURATION)) { -it } + fadeIn(
                            tween(
                                SLIDE_DURATION
                            )
                        )).togetherWith(slideOutHorizontally(tween(SLIDE_DURATION)) { it } + fadeOut(
                            tween(
                                SLIDE_DURATION
                            )
                        ))
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = if (isKeyboardOpen) 34.dp else 0.dp)
                    .background(Color(0xFFFBFBFB)),
                label = "formStep",
            ) { step ->
                when (step) {
                    FormStep.ENTER_NUMBER -> EnterNumberForm(
                        phoneNumber = phoneNumber,
                        onPhoneChange = { phoneNumber = it },
                        onNext = { formStep = FormStep.ENTER_OTP },
                    )

                    FormStep.ENTER_OTP -> EnterOtpForm(
                        phoneNumber = phoneNumber,
                        otp = otp,
                        onOtpChange = { otp = it },
                        timer = otpTimer,
                        canResend = canResend,
                        onResend = {
                            otp = ""
                            otpTimer = RESEND_COUNTDOWN
                            canResend = false
                        },
                        onEditNumber = { formStep = FormStep.ENTER_NUMBER },
                        onSubmit = {
                            keyboardController?.hide()
                            onOtpVerified()
                        },
                        otpFocusRequester = otpFocusRequester,
                    )
                }
            }

            Spacer(Modifier.weight(1f))
        }
    }
}


@Composable
private fun EnterNumberForm(
    phoneNumber: String,
    onPhoneChange: (String) -> Unit,
    onNext: () -> Unit,
) {
    val colors = MaterialTheme.catalogColors
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 20.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            text = "Catalog Banao Sales Badhao!",
            fontSize = 20.sp,
            fontWeight = FontWeight.W600,
            color = SlateGrayBlue,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp),
        )

        Spacer(Modifier.height(40.dp))

        Text(
            text = "Mobile Number",
            fontSize = 15.sp,
            fontWeight = FontWeight.W400,
            color = SlateGrayBlue,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp),
        )

        Spacer(Modifier.height(8.dp))

        OutlinedTextField(
            value = phoneNumber,
            onValueChange = { onPhoneChange(it.filter(Char::isDigit).take(10)) },
            placeholder = { Text("Enter Mobile Number", color = colors.grayPlaceholder) },
            prefix = {
                Text(
                    text = "+91  ",
                    color = colors.navyDark,
                    fontWeight = FontWeight.Medium,
                )
            },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
            singleLine = true,
            shape = RoundedCornerShape(8.dp),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = colors.purpleLight,
                unfocusedBorderColor = colors.grayBorder,
                focusedContainerColor = Color.White,
                unfocusedContainerColor = Color.White,
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp),
        )

        Spacer(Modifier.height(32.dp))

        Button(
            onClick = onNext,
            enabled = phoneNumber.length == 10,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp)
                .height(52.dp),
            shape = RoundedCornerShape(8.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = colors.purpleLight,
                disabledContainerColor = colors.purpleLight.copy(alpha = 0.45f),
                contentColor = Color.White,
                disabledContentColor = Color.White,
            ),
        ) {
            Text("Next", fontSize = 16.sp, fontWeight = FontWeight.Medium)
        }
    }
}


@Composable
private fun EnterOtpForm(
    phoneNumber: String,
    otp: String,
    onOtpChange: (String) -> Unit,
    timer: Int,
    canResend: Boolean,
    onResend: () -> Unit,
    onEditNumber: () -> Unit,
    onSubmit: () -> Unit,
    otpFocusRequester: FocusRequester,
) {
    val colors = MaterialTheme.catalogColors

    LaunchedEffect(Unit) {
        otpFocusRequester.requestFocus()
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 20.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            text = "Enter OTP",
            fontSize = 20.sp,
            fontWeight = FontWeight.W600,
            color = SlateGrayBlue,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp),
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(
                text = "Enter 6 digit OTP sent on $phoneNumber",
                fontSize = 13.sp,
                fontWeight = FontWeight.W400,
                color = SlateGrayBlue,
            )
            Spacer(Modifier.width(4.dp))
            IconButton(
                onClick = onEditNumber,
                modifier = Modifier.size(20.dp),
                colors = IconButtonDefaults.iconButtonColors(contentColor = colors.purpleLight),
            ) {
                Icon(
                    imageVector = Icons.Filled.Edit,
                    contentDescription = "Edit number",
                    modifier = Modifier.size(16.dp),
                )
            }
        }

        Spacer(Modifier.height(32.dp))

        OutlinedTextField(
            value = otp,
            onValueChange = { if (it.length <= 6) onOtpChange(it.filter(Char::isDigit)) },
            placeholder = { Text("Enter 6 digits here", color = colors.grayPlaceholder) },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.NumberPassword),
            singleLine = true,
            shape = RoundedCornerShape(8.dp),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = colors.purpleLight,
                unfocusedBorderColor = colors.grayBorderLight,
                focusedContainerColor = Color.White,
                unfocusedContainerColor = Color.White,
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp)
                .focusRequester(otpFocusRequester),
        )

        Spacer(Modifier.height(12.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            if (!canResend) {
                Text(
                    "Code expires in...  ",
                    fontSize = 13.sp,
                    fontWeight = FontWeight.W400,
                    color = SlateGrayBlue,
                )
                Text(
                    text = "${timer}s",
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Bold,
                    color = colors.purpleLight,
                )
            }
            Spacer(Modifier.width(12.dp))
            Button(
                onClick = onResend,
                enabled = canResend,
                colors = ButtonDefaults.buttonColors().copy(containerColor = LavenderMist),
                shape = RoundedCornerShape(8.dp),
                contentPadding = PaddingValues(horizontal = 16.dp, vertical = 4.dp),
                modifier = Modifier.height(32.dp),
            ) {
                Text(
                    modifier = Modifier,
                    text = "Resend",
                    fontSize = 13.sp,
                    color = DarkSlateGrayBlue,
                    fontWeight = FontWeight.W400
                )
            }
        }

        Spacer(Modifier.height(32.dp))

        Button(
            onClick = onSubmit,
            enabled = otp.length == 6,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp)
                .height(52.dp),
            shape = RoundedCornerShape(8.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = colors.purpleLight,
                disabledContainerColor = colors.purpleLight.copy(alpha = 0.45f),
                contentColor = Color.White,
                disabledContentColor = Color.White,
            ),
        ) {
            Text("Submit", fontSize = 16.sp, fontWeight = FontWeight.SemiBold)
        }
    }
}

@Composable
fun SetWhiteStatusBar() {
    val view = LocalView.current

    SideEffect {
        val window = (view.context as Activity).window
        WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = false
    }
}

@Preview
@Composable
fun PreviewOnboardingScreen() {
    OnboardingScreen(onOtpVerified = {})
}