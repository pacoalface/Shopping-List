package com.tfm.shoppinglist.ui

import com.tfm.shoppinglist.ui.models.ShoppingListItem

sealed class ShoppingListScreenState {
    object Loading: ShoppingListScreenState()
    object EmptyState: ShoppingListScreenState()
    data class Success(val items: List<ShoppingListItem>): ShoppingListScreenState()
}
