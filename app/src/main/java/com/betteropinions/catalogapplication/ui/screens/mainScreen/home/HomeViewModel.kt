package com.betteropinions.catalogapplication.ui.screens.mainScreen.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.betteropinions.catalogapplication.R
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

@OptIn(FlowPreview::class)
class HomeViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState: StateFlow<HomeUiState> = _uiState.asStateFlow()

    private val _uiEvent = MutableSharedFlow<HomeUiEvent>()
    val uiEvent: SharedFlow<HomeUiEvent> = _uiEvent.asSharedFlow()

    private val searchQueryFlow = MutableStateFlow("")

    init {
        loadCatalogItems()
        observeSearchQuery()
    }

    fun onAction(action: HomeUiAction) {
        when (action) {
            is HomeUiAction.OnTryItOutClicked -> {
                viewModelScope.launch {
                    _uiEvent.emit(HomeUiEvent.ShowPaywallDialog(action.itemId))
                }
            }

            HomeUiAction.OnPaywallDismissed -> {
                viewModelScope.launch {
                    _uiEvent.emit(HomeUiEvent.DismissDialog)
                }
            }

            HomeUiAction.OnPayClicked -> {
                viewModelScope.launch {
                    _uiEvent.emit(HomeUiEvent.ShowThanksDialog)
                }
            }

            HomeUiAction.OnThanksDismissed -> {
                viewModelScope.launch {
                    _uiEvent.emit(HomeUiEvent.DismissDialog)
                }
            }

            HomeUiAction.OnSearchIconClicked -> {
                _uiState.update { it.copy(isSearchExpanded = true) }
            }

            HomeUiAction.OnCloseSearchClicked -> {
                searchQueryFlow.value = ""
                _uiState.update {
                    it.copy(
                        isSearchExpanded = false,
                        searchQuery = "",
                        isSearching = false,
                        filteredItems = it.catalogItems,
                    )
                }
            }

            is HomeUiAction.OnSearchQueryChanged -> {
                _uiState.update {
                    it.copy(
                        searchQuery = action.query,
                        isSearching = action.query.isNotEmpty(),
                    )
                }
                searchQueryFlow.value = action.query
            }
        }
    }

    private fun observeSearchQuery() {
        searchQueryFlow
            .debounce(500)
            .distinctUntilChanged()
            .onEach { query ->
                val allItems = _uiState.value.catalogItems
                val filtered = if (query.isBlank()) {
                    allItems
                } else {
                    allItems.filter { it.title.startsWith(query, ignoreCase = true) }
                }
                _uiState.update {
                    it.copy(
                        filteredItems = filtered,
                        isSearching = false,
                    )
                }
            }
            .launchIn(viewModelScope)
    }

    private fun loadCatalogItems() {
        val items = listOf(
            CatalogItem(
                id = 1,
                title = "Catalog",
                afterImageRes = R.drawable.img_catalog1_after,
                beforeImageRes = R.drawable.img_catalog1_before,
            ),
            CatalogItem(
                id = 2,
                title = "Catalog",
                afterImageRes = R.drawable.img_catalog1_after,
                beforeImageRes = R.drawable.img_catalog1_before,
            ),
            CatalogItem(
                id = 3,
                title = "Catalog",
                afterImageRes = R.drawable.img_catalog1_after,
                beforeImageRes = R.drawable.img_catalog1_before,
            ),
            CatalogItem(
                id = 4,
                title = "Catalog",
                afterImageRes = R.drawable.img_catalog1_after,
                beforeImageRes = R.drawable.img_catalog1_before,
            ),
            CatalogItem(
                id = 5,
                title = "A-Catalog",
                afterImageRes = R.drawable.img_catalog1_after,
                beforeImageRes = R.drawable.img_catalog1_before,
            ),
            CatalogItem(
                id = 6,
                title = "AA-Catalog",
                afterImageRes = R.drawable.img_catalog1_after,
                beforeImageRes = R.drawable.img_catalog1_before,
            ),
            CatalogItem(
                id = 7,
                title = "AAA-Catalog",
                afterImageRes = R.drawable.img_catalog1_after,
                beforeImageRes = R.drawable.img_catalog1_before,
            )
        )
        _uiState.update { it.copy(catalogItems = items, filteredItems = items) }
    }
}
