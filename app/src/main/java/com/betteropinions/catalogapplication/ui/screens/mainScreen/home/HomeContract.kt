package com.betteropinions.catalogapplication.ui.screens.mainScreen.home

data class HomeUiState(
    val catalogItems: List<CatalogItem> = emptyList(),
    val filteredItems: List<CatalogItem> = emptyList(),
    val searchQuery: String = "",
    val isSearchExpanded: Boolean = false,
    val isSearching: Boolean = false,
)

sealed interface HomeUiEvent {
    data class ShowPaywallDialog(val itemId: Int) : HomeUiEvent
    data object ShowThanksDialog : HomeUiEvent
    data object DismissDialog : HomeUiEvent
}

sealed interface HomeUiAction {
    data class OnTryItOutClicked(val itemId: Int) : HomeUiAction
    data object OnPaywallDismissed : HomeUiAction
    data object OnPayClicked : HomeUiAction
    data object OnThanksDismissed : HomeUiAction
    data object OnSearchIconClicked : HomeUiAction
    data object OnCloseSearchClicked : HomeUiAction
    data class OnSearchQueryChanged(val query: String) : HomeUiAction
}
