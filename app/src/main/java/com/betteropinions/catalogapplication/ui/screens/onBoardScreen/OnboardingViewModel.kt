package com.betteropinions.catalogapplication.ui.screens.onBoardScreen

import androidx.lifecycle.ViewModel
import com.betteropinions.catalogapplication.R
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class OnboardingViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(OnboardingUiState())
    val uiState: StateFlow<OnboardingUiState> = _uiState.asStateFlow()

    init {
        loadSlides()
    }

    private fun loadSlides() {
        val slides = listOf(
            BeforeAfterSlide(
                beforeImageRes = R.drawable.img_onboard1_before,
                afterImageRes = R.drawable.img_onboard1_after,
            ),
            BeforeAfterSlide(
                beforeImageRes = R.drawable.img_onboard2_before,
                afterImageRes = R.drawable.img_onboard2_after,
            ),
            BeforeAfterSlide(
                beforeImageRes = R.drawable.img_onboard3_before,
                afterImageRes = R.drawable.img_onboard3_after,
            ),
        )
        _uiState.update { it.copy(slides = slides) }
    }
}
