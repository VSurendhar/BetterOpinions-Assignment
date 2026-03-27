package com.betteropinions.catalogapplication.ui.screens.onBoardScreen

data class OnboardingUiState(
    val slides: List<BeforeAfterSlide> = emptyList(),
)

sealed interface OnboardingUiEvent

sealed interface OnboardingUiAction
