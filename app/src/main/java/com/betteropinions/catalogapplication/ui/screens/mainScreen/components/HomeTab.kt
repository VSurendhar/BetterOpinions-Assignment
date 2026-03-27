package com.betteropinions.catalogapplication.ui.screens.mainScreen.components


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.betteropinions.catalogapplication.ui.dialogs.PaywallDialog
import com.betteropinions.catalogapplication.ui.dialogs.ThanksDialog
import com.betteropinions.catalogapplication.ui.screens.mainScreen.home.HomeUiAction
import com.betteropinions.catalogapplication.ui.screens.mainScreen.home.HomeUiEvent
import com.betteropinions.catalogapplication.ui.screens.mainScreen.home.HomeViewModel
import com.betteropinions.catalogapplication.ui.theme.InterFontFamily
import com.betteropinions.catalogapplication.ui.theme.catalogColors

private enum class DialogType { NONE, PAYWALL, THANKS }

@Composable
fun HomeTab(
    modifier: Modifier,
    viewModel: HomeViewModel = viewModel(),
) {
    val colors = MaterialTheme.catalogColors
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    var activeDialog by remember { mutableStateOf(DialogType.NONE) }

    LaunchedEffect(Unit) {
        viewModel.uiEvent.collect { event ->
            activeDialog = when (event) {
                is HomeUiEvent.ShowPaywallDialog -> DialogType.PAYWALL
                is HomeUiEvent.ShowThanksDialog -> DialogType.THANKS
                is HomeUiEvent.DismissDialog -> DialogType.NONE
            }
        }
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .background(colors.purple)
                .padding(horizontal = 16.dp, vertical = 12.dp)
        ) {
            if (uiState.isSearchExpanded) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    OutlinedTextField(
                        maxLines = 1,
                        value = uiState.searchQuery,
                        onValueChange = { viewModel.onAction(HomeUiAction.OnSearchQueryChanged(it)) },
                        modifier = Modifier
                            .weight(1f),
                        placeholder = {
                            Text(
                                "Search catalog...",
                                color = Color.White.copy(alpha = 0.7f)
                            )
                        },
                        singleLine = true,
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = Color.White,
                            unfocusedBorderColor = Color.White.copy(alpha = 0.5f),
                            focusedTextColor = Color.White,
                            unfocusedTextColor = Color.White,
                            cursorColor = Color.White,
                        ),
                        shape = RoundedCornerShape(8.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    IconButton(onClick = { viewModel.onAction(HomeUiAction.OnCloseSearchClicked) }) {
                        Icon(
                            Icons.Default.Close,
                            contentDescription = "Close search",
                            tint = Color.White
                        )
                    }
                }
            } else {
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "Home",
                        color = Color.White,
                        fontSize = 18.sp,
                        fontFamily = InterFontFamily,
                        fontWeight = FontWeight.W500,
                    )
                    IconButton(onClick = { viewModel.onAction(HomeUiAction.OnSearchIconClicked) }) {
                        Icon(
                            Icons.Default.Search,
                            contentDescription = "Search",
                            tint = Color.White
                        )
                    }
                }
            }
        }

        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            if (uiState.isSearching) {
                CircularProgressIndicator(color = colors.purple)
            } else {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 16.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                    contentPadding = PaddingValues(vertical = 16.dp)
                ) {
                    items(uiState.filteredItems, key = { it.id }) { item ->
                        CatalogCard(
                            title = item.title,
                            afterImageRes = item.afterImageRes,
                            beforeImageRes = item.beforeImageRes,
                            onTryItOutClick = {
                                viewModel.onAction(HomeUiAction.OnTryItOutClicked(item.id))
                            },
                        )
                    }
                }
            }
        }
    }

    when (activeDialog) {
        DialogType.PAYWALL -> {
            PaywallDialog(
                onDismiss = { viewModel.onAction(HomeUiAction.OnPaywallDismissed) },
                onPayClick = { viewModel.onAction(HomeUiAction.OnPayClicked) },
            )
        }

        DialogType.THANKS -> {
            ThanksDialog(
                onDismiss = { viewModel.onAction(HomeUiAction.OnThanksDismissed) },
            )
        }

        DialogType.NONE -> {}
    }
}
