package com.betteropinions.catalogapplication.ui.screens.mainScreen.home

import androidx.annotation.DrawableRes

data class CatalogItem(
    val id: Int,
    val title: String,
    @DrawableRes val afterImageRes: Int,
    @DrawableRes val beforeImageRes: Int,
)
